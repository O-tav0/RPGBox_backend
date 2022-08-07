package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.entity.Personagem;
import br.com.rpgbox.RPGBox.enums.EnumTipoEventoCampanha;
import br.com.rpgbox.RPGBox.entity.Campanha;
import br.com.rpgbox.RPGBox.entity.EventoCampanha;
import br.com.rpgbox.RPGBox.entity.TipoEventoCampanha;
import br.com.rpgbox.RPGBox.repository.EventoCampanhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EventoCampanhaService {

    @Autowired
    private EventoCampanhaRepository eventoCampanhaRepository;

    @Autowired
    private TipoEventoCampanhaService tipoEventoCampanhaService;

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
}
