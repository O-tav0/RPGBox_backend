package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.DTO.HabilidadeDTO;
import br.com.rpgbox.RPGBox.DTO.PersonagemDTO;
import br.com.rpgbox.RPGBox.VO.HabilidadeVO;
import br.com.rpgbox.RPGBox.VO.PersonagemVO;
import br.com.rpgbox.RPGBox.entity.Campanha;
import br.com.rpgbox.RPGBox.entity.Habilidade;
import br.com.rpgbox.RPGBox.entity.Personagem;
import br.com.rpgbox.RPGBox.enums.TipoDePersonagem;
import br.com.rpgbox.RPGBox.repository.HabilidadeRepository;
import br.com.rpgbox.RPGBox.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private HabilidadeRepository habilidadeRepository;

    @Autowired
    private TipoHabilidadeService tipoHabilidadeService;

    @Autowired
    private TipoPersonagemService tipoPersonagemService;

    @Autowired
    private CampanhaService campanhaService;

    @Autowired
    private EventoCampanhaService eventoCampanhaService;

    @Autowired
    private HabilidadeService habilidadeService;

    @Value("${imagem.aventureiro}")
    private String imgDefaultAventureiro;

    @Value("${imagem.npc}")
    private String imgDefaultNpc;

    @Value("${imagem.inimigo}")
    private String imgDefaultInimigo;

    public void criarNovoPersonagem(PersonagemVO vo) {

        Campanha campanha = campanhaService.buscarCampanhaPorId(vo.getSqCampanha());
        Personagem novoPersonagem = new Personagem(campanha);
        List<Habilidade> habilidades = preencherHabilidadesPersonagem(vo.getHabilidadesPersonagem(), novoPersonagem);

        novoPersonagem = salvarPersonagem(habilidades, vo, novoPersonagem);
        salvarHabilidadesDoPersonagem(habilidades);
        salvarEventoCriacaoPersonagem(campanha, novoPersonagem);
    }

    public List<Habilidade> preencherHabilidadesPersonagem(List<HabilidadeVO> habilidades, Personagem novoPersonagem) {
        List<Habilidade> habilidadesDoPersonagem = new ArrayList<Habilidade>();

        for(HabilidadeVO habilidadeVO: habilidades) {
            Habilidade novaHabilidade = new Habilidade();

            novaHabilidade.setNomeHabilidade(habilidadeVO.getNomeHabilidade());
            novaHabilidade.setDescricaoHabilidade(habilidadeVO.getDescricaoHabilidade());

            novaHabilidade.setTipoHabilidade(tipoHabilidadeService.
                    buscarTipoHabilidade(habilidadeVO.getTipoHabilidade()));

            novaHabilidade.setPersonagem(novoPersonagem);

            habilidadesDoPersonagem.add(novaHabilidade);
        }
        return habilidadesDoPersonagem;
    }

    public Personagem salvarPersonagem(List<Habilidade> listaHabilidades, PersonagemVO vo, Personagem novoPersonagem) {

        novoPersonagem.setHabilidadesPersonagem(listaHabilidades);
        novoPersonagem.setNomePersonagem(vo.getNomePersonagem());
        novoPersonagem.setNivelPersonagem(vo.getNivelPersonagem());
        novoPersonagem.setClassePersonagem(vo.getClassePersonagem());
        novoPersonagem.setImagemPersonagem(tratarImagemPersonagem(vo.getImagem(), vo.getTipoPersonagem()));
        novoPersonagem.setRacaPersonagem(vo.getRacaPersonagem());
        novoPersonagem.setTipoPersonagem(tipoPersonagemService.buscarTipoPersonagem(vo.getTipoPersonagem()));
        novoPersonagem.setPontosDeVida(vo.getPontosVida());

        return personagemRepository.save(novoPersonagem);
    }

    public void salvarHabilidadesDoPersonagem(List<Habilidade> habilidadesParaSalvar) {
        for(Habilidade habilidade: habilidadesParaSalvar) {
            habilidadeRepository.save(habilidade);
        }
    }

    public void salvarEventoCriacaoPersonagem(Campanha campanha, Personagem personagem) {
        eventoCampanhaService.gerarEventoCriacaoPersonagem(campanha, personagem);
    }

    public Personagem buscarPersonagemPorId(Long sqPersonagem) {
        return personagemRepository.findById(sqPersonagem).get();
    }

    public PersonagemDTO converteEmDTO(Personagem personagem) {
        PersonagemDTO dto = new PersonagemDTO();
        List<HabilidadeDTO> habilidades = new ArrayList<HabilidadeDTO>();

        for(Habilidade habilidade: personagem.getHabilidadesPersonagem()) {
            habilidades.add(habilidadeService.converteEmDTO(habilidade));
        }

        dto.setClassePersonagem(personagem.getClassePersonagem());
        dto.setSqPersonagem(personagem.getSqPersonagem());
        dto.setHabilidadesPersonagem(habilidades);
        dto.setNomePersonagem(personagem.getNomePersonagem());
        dto.setNivelPersonagem(personagem.getNivelPersonagem());
        dto.setClassePersonagem(personagem.getClassePersonagem());
        dto.setImagemPersonagem(personagem.getImagemPersonagem());
        dto.setRacaPersonagem(personagem.getRacaPersonagem());
        dto.setTipoPersonagem(personagem.getTipoPersonagem().getDsTipoPersonagem());
        dto.setPontosDeVida(personagem.getPontosDeVida());

        return dto;
    }

    public String tratarImagemPersonagem(String imagemEnviada, TipoDePersonagem.TipoPersonagemEnum tipoDoPersonagem) {

        String imgRetorno = imagemEnviada;

        if(imagemEnviada == null || imagemEnviada.equals("")) {
            if (tipoDoPersonagem.equals(TipoDePersonagem.TipoPersonagemEnum.AVENTUREIRO)) {
                imgRetorno = imgDefaultAventureiro;
            } else if (tipoDoPersonagem.equals(TipoDePersonagem.TipoPersonagemEnum.NPC)) {
                imgRetorno = imgDefaultNpc;
            } else if (tipoDoPersonagem.equals(TipoDePersonagem.TipoPersonagemEnum.INIMIGO)) {
                imgRetorno = imgDefaultInimigo;
            }
        }
        return imgRetorno;
    }
}
