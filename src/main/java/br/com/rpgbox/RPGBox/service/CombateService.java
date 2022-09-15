package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.VO.CombateVO;
import br.com.rpgbox.RPGBox.VO.PersonagemCombateVO;
import br.com.rpgbox.RPGBox.entity.*;
import br.com.rpgbox.RPGBox.enums.EnumStatusCombate;
import br.com.rpgbox.RPGBox.repository.CombateRepository;
import br.com.rpgbox.RPGBox.repository.PersonagemCombateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
