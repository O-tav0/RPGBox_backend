package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.DTO.EventoCampanhaDTO;
import br.com.rpgbox.RPGBox.entity.Personagem;
import br.com.rpgbox.RPGBox.enums.EnumTipoEventoCampanha;
import br.com.rpgbox.RPGBox.entity.Campanha;
import br.com.rpgbox.RPGBox.entity.EventoCampanha;
import br.com.rpgbox.RPGBox.entity.TipoEventoCampanha;
import br.com.rpgbox.RPGBox.repository.EventoCampanhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventoCampanhaService {

    @Autowired
    private EventoCampanhaRepository eventoCampanhaRepository;

    @Autowired
    private CampanhaService campanhaService;

    @Autowired
    private TipoEventoCampanhaService tipoEventoCampanhaService;

    @Autowired
    private PersonagemService personagemService;

    public void gerarEventoCriacaoCampanha(Campanha campanha) {
        EventoCampanha eventoCriacaoCampanha = preencherObjetoEventoCriacaoCampanha(campanha);
        eventoCampanhaRepository.save(eventoCriacaoCampanha);
    }

    private EventoCampanha preencherObjetoEventoCriacaoCampanha(Campanha campanha) {
        EventoCampanha evento = new EventoCampanha();
        TipoEventoCampanha tipoEvento = tipoEventoCampanhaService.buscarTipoEventoCampanha(EnumTipoEventoCampanha.TipoEventoCampanhaEnum.CRIACAO_CAMPANHA);

        evento.setCampanha(campanha);
        evento.setDataEvento(new Date());
        evento.setTipoEventoCampanha(tipoEvento);

        return evento;
    }

    public EventoCampanha buscarEventoCriacaoCampanha(Campanha campanha) {
        TipoEventoCampanha eventoCriacao = tipoEventoCampanhaService.buscarTipoEventoCampanha(EnumTipoEventoCampanha.TipoEventoCampanhaEnum.CRIACAO_CAMPANHA);
        return eventoCampanhaRepository.findByCampanhaAndTipoEventoCampanha(campanha, eventoCriacao);
    }

    public void gerarEventoCriacaoPersonagem(Campanha campanha, Personagem personagem) {
        EventoCampanha eventoCriacaoPersonagem = preencherObjetoEventoCriacaoPersonagem(campanha, personagem);
        eventoCampanhaRepository.save(eventoCriacaoPersonagem);
    }

    private EventoCampanha preencherObjetoEventoCriacaoPersonagem(Campanha campanha, Personagem personagem) {
        EventoCampanha evento = new EventoCampanha();
        TipoEventoCampanha tipoEvento = tipoEventoCampanhaService.buscarTipoEventoCampanha(EnumTipoEventoCampanha.TipoEventoCampanhaEnum.ADICAO_PERSONAGEM);

        evento.setCampanha(campanha);
        evento.setPersonagem(personagem);
        evento.setDataEvento(new Date());
        evento.setTipoEventoCampanha(tipoEvento);

        return evento;
    }

    public List<EventoCampanhaDTO> buscarHistoricoCampanha(Long sqCampanha) {
        Campanha campanha  = campanhaService.buscarCampanhaPorId(sqCampanha);

        List<EventoCampanha> eventosDaCampanha = eventoCampanhaRepository.findAllByCampanha(campanha);

        return converteParaDto(eventosDaCampanha);
    }

    public List<EventoCampanhaDTO> converteParaDto(List<EventoCampanha> eventosDaCampanha) {
        List<EventoCampanhaDTO> historicoCampanha = new ArrayList<EventoCampanhaDTO>();

        for(EventoCampanha evento: eventosDaCampanha) {
            EventoCampanhaDTO dto = preecheRetornoHistoricoCampanha(evento);
            historicoCampanha.add(dto);
        }

        return historicoCampanha;
    }

    public EventoCampanhaDTO preecheRetornoHistoricoCampanha(EventoCampanha evento) {
        EventoCampanhaDTO dto = new EventoCampanhaDTO();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        dto.setSqEventoCampanha(evento.getSqEventoCampanha());
        dto.setDataEvento(sdf.format(evento.getDataEvento()));
        dto.setDescricaoEventoCampanha(evento.getTipoEventoCampanha().getDescricaoEventoCampanha());

        if(evento.isEventoPersonagem()) {
            dto.setPersonagem(personagemService.converteEmDTO(evento.getPersonagem()));
        }
        else if(evento.isEventoCombate()) {
            dto.setAnotacao(evento.getAnotacao());
        }
        else if(evento.isEventoAnotacao()){
            dto.setCombate(evento.getCombate());
        }

        return dto;
    }


}
