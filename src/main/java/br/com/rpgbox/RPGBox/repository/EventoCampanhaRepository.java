package br.com.rpgbox.RPGBox.repository;

import br.com.rpgbox.RPGBox.entity.Campanha;
import br.com.rpgbox.RPGBox.entity.EventoCampanha;
import br.com.rpgbox.RPGBox.entity.StatusCombate;
import br.com.rpgbox.RPGBox.entity.TipoEventoCampanha;
import org.springframework.data.repository.CrudRepository;

public interface EventoCampanhaRepository extends CrudRepository<EventoCampanha, Long> {

    public EventoCampanha findByCampanhaAndTipoEventoCampanha(Campanha campanha, TipoEventoCampanha tipoEvento);
}
