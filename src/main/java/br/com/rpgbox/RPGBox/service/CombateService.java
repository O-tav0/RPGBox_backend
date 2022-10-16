package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.DTO.CombateDTO;
import br.com.rpgbox.RPGBox.DTO.HabilidadeDTO;
import br.com.rpgbox.RPGBox.DTO.PersonagemCombateDTO;
import br.com.rpgbox.RPGBox.DTO.PersonagemDTO;
import br.com.rpgbox.RPGBox.VO.*;
import br.com.rpgbox.RPGBox.entity.*;
import br.com.rpgbox.RPGBox.enums.EnumStatusCombate;
import br.com.rpgbox.RPGBox.repository.CombateRepository;
import br.com.rpgbox.RPGBox.repository.PersonagemCombateRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CombateService {

    @Autowired
    private CampanhaService campanhaService;

    @Autowired
    private PersonagemService personagemService;

    @Autowired
    private StatusCombateService statusCombateService;

    @Autowired
    private CombateRepository combateRepository;

    @Autowired
    private PersonagemCombateRepository personagemCombateRepository;

    @Autowired
    private EventoCampanhaService eventoCampanhaService;

    @Autowired
    private TipoPersonagemService tipoPersonagemService;

    public void criarNovoCombate(CombateVO novoCombate) {

        Campanha campanha = campanhaService.buscarCampanhaPorId(novoCombate.getSqCampanha());
        Combate combate = new Combate(campanha);
        List<PersonagemCombate> personagensDoCombate = preencherPersonagensDoCombate(novoCombate.getPersonagensCombate(), combate);

        combate = salvarNovoCombate(personagensDoCombate, novoCombate, combate);
        salvarPersonagensCombate(personagensDoCombate);
        salvarEventoCriacaoCombate(campanha, combate);

    }

    public List<PersonagemCombate> preencherPersonagensDoCombate(List<PersonagemCombateVO> personagens, Combate combate) {
        List<PersonagemCombate> lista = new ArrayList<PersonagemCombate>();

        for(PersonagemCombateVO vo: personagens) {
            Personagem entidade = personagemService.buscarPersonagemPorId(vo.getSqPersonagem());
            PersonagemCombate personagemDoCombate = new PersonagemCombate();

            personagemDoCombate.setCombate(combate);

            personagemDoCombate.setNrOrdemCombate((long) (personagens.indexOf(vo) + 1));
            personagemDoCombate.setPersonagem(entidade);

            lista.add(personagemDoCombate);
        }

        return lista;

    }

    public Combate salvarNovoCombate(List<PersonagemCombate> listaPersonagens, CombateVO vo, Combate combate) {

        combate.setDtCombate(new Date());
        combate.setStatusCombate(statusCombateService.buscarStatusCombate(EnumStatusCombate.StatusCombateEnum.PENDENTE));
        combate.setPersonagensDoCombate(listaPersonagens);
        combate.setTituloCombate(vo.getTituloCombate());

        return combateRepository.save(combate);
    }

    public void salvarPersonagensCombate(List<PersonagemCombate> lista) {
        for(PersonagemCombate personagem: lista) {
            personagemCombateRepository.save(personagem);
        }
    }

    public void salvarEventoCriacaoCombate(Campanha campanha, Combate combate) {
        eventoCampanhaService.gerarEventoCriacaoCombate(campanha, combate);
    }

    public List<CombateDTO> buscarCombatesDaCampanha(Long sqCampanha) {
        Campanha campanha = campanhaService.buscarCampanhaPorId(sqCampanha);
        List<CombateDTO> lista = new ArrayList<CombateDTO>();
        if(Objects.nonNull(campanha)) {
            List<Combate> combates = combateRepository.findAllByCampanha(campanha);
            Collections.sort(combates, new Comparator<Combate>() {
                @Override
                public int compare(Combate u1, Combate u2) {
                    return u1.getSqCombate().compareTo(u2.getSqCombate());
                }
            });
            for(Combate combate: combates) {
                lista.add(converteEmDTO(combate));
            }
            return lista;
        } else {
            throw new EntityNotFoundException();
        }
    }

    public CombateDTO converteEmDTO(Combate combate) {
        SimpleDateFormat  sdf = new SimpleDateFormat("dd/MM/yyyy");
        CombateDTO dto = new CombateDTO();
        CombateLog log = new CombateLog();
        List<Turno> turnosCombate = new ArrayList<Turno>();


        if(Objects.nonNull(combate.getResumoCombate())) {
            JSONObject obj = new JSONObject(combate.getResumoCombate());
            JSONArray array = obj.getJSONArray("resumoCombate");

            for(int i = 0; i < array.length(); i++) {
                Turno turnoLog = new Turno();
                List<Acao> acoesDoTurno = new ArrayList<Acao>();
                JSONObject turno = array.getJSONObject(i);

                turnoLog.setNumeroTurno(turno.getInt("numeroTurno"));

                JSONArray acoes = turno.getJSONArray("acoesDoTurno");

                for(int j=0; j < acoes.length(); j++) {
                    JSONObject objAcao = acoes.getJSONObject(j);

                    Acao acaoRetorno = new Acao();

                    acaoRetorno.setDanoCausado(objAcao.getInt("danoCausado"));

                    JSONObject habilidade = objAcao.getJSONObject("habilidadeUtilizada");
                    HabilidadeDTO habDTO = new HabilidadeDTO();
                    habDTO.setDescricaoHabilidade(habilidade.getString("nomeHabilidade"));
                    habDTO.setTipoHabilidade(habilidade.getString("tipoHabilidade"));
                    habDTO.setDescricaoHabilidade(habilidade.getString("descricaoHabilidade"));
                    acaoRetorno.setHabilidadeUtilizada(habDTO);

                    JSONObject queUtilizou = objAcao.getJSONObject("personagemQueUtilizou");
                    PersonagemLog personagemQueUtilizou = new PersonagemLog();
                    personagemQueUtilizou.setClassePersonagem(queUtilizou.getString("classePersonagem"));
                    personagemQueUtilizou.setNivelPersonagem(queUtilizou.getInt("nivelPersonagem"));
                    personagemQueUtilizou.setNomePersonagem(queUtilizou.getString("nomePersonagem"));
                    personagemQueUtilizou.setSqPersonagem(queUtilizou.getLong("sqPersonagem"));
                    personagemQueUtilizou.setTipoPersonagem(queUtilizou.getString("tipoPersonagem"));
                    personagemQueUtilizou.setRacaPersonagem(queUtilizou.getString("racaPersonagem"));

                    JSONObject alvo = objAcao.getJSONObject("personagemAlvo");
                    PersonagemLog personagemAlvo = new PersonagemLog();
                    personagemAlvo.setClassePersonagem(alvo.getString("classePersonagem"));
                    personagemAlvo.setNivelPersonagem(alvo.getInt("nivelPersonagem"));
                    personagemAlvo.setNomePersonagem(alvo.getString("nomePersonagem"));
                    personagemAlvo.setSqPersonagem(alvo.getLong("sqPersonagem"));
                    personagemAlvo.setTipoPersonagem(alvo.getString("tipoPersonagem"));
                    personagemAlvo.setRacaPersonagem(alvo.getString("racaPersonagem"));

                    acaoRetorno.setPersonagemAlvo(personagemAlvo);
                    acaoRetorno.setPersonagemQueUtilizou(personagemQueUtilizou);

                    acoesDoTurno.add(acaoRetorno);
                    turnoLog.setAcoesDoTurno(acoesDoTurno);

                }
                turnosCombate.add(turnoLog);
            }
            
        }

        log.setResumoCombate(turnosCombate);
        dto.setDtCombate(sdf.format(combate.getDtCombate()));
        dto.setStatusCombate(combate.getStatusCombate().getDsStatusCombate());
        dto.setTituloCombate(combate.getTituloCombate());
        dto.setSqCombate(combate.getSqCombate());
        dto.setPersonagensDoCombate(retornaPersonagensCombateDTO(combate.getPersonagensDoCombate()));
        dto.setResumoCombate(log);

        return dto;
    }

    public List<PersonagemCombateDTO> retornaPersonagensCombateDTO(List<PersonagemCombate> personagens) {
        List<PersonagemCombateDTO> listaRetorno = new ArrayList<PersonagemCombateDTO>();

        Collections.sort(personagens, new Comparator<PersonagemCombate>() {
            @Override
            public int compare(PersonagemCombate u1, PersonagemCombate u2) {
                return u1.getNrOrdemCombate().compareTo(u2.getNrOrdemCombate());
            }
        });

        for(PersonagemCombate persComb: personagens) {
              PersonagemCombateDTO dto = new PersonagemCombateDTO();

              dto.setPersonagem(personagemService.converteEmDTO(persComb.getPersonagem()));
              dto.setNrOrdemCombate(persComb.getNrOrdemCombate());

              listaRetorno.add(dto);
        }

        return listaRetorno;
    }

    public CombateDTO buscarCombate(Long sqCombate) throws EntityNotFoundException{
        Combate combate = combateRepository.findById(sqCombate).get();

        if(Objects.nonNull(combate)) {
            return converteEmDTO(combate);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void finalizarCombate(Long sqCombate, CombateLog resumoCombate) {
        Combate combate = combateRepository.findById(sqCombate).get();
        StatusCombate status = statusCombateService.buscarStatusCombate(EnumStatusCombate.StatusCombateEnum.FINALIZADO);

        JSONObject obj = new JSONObject(resumoCombate);

        combate.setResumoCombate(obj.toString());
        combate.setStatusCombate(status);
        combateRepository.save(combate);
    }
}
