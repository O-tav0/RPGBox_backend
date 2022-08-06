package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.Enum.EnumTipoEventoCampanha;
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
}