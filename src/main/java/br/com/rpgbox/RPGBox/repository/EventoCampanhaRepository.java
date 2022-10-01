package br.com.rpgbox.RPGBox.repository;

import br.com.rpgbox.RPGBox.entity.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventoCampanhaRepository extends CrudRepository<EventoCampanha, Long> {

    public EventoCampanha findByCampanhaAndTipoEventoCampanha(Campanha campanha, TipoEventoCampanha tipoEvento);

    public List<EventoCampanha> findAllByCampanha(Campanha campanha);

    public EventoCampanha findByAnotacao(Anotacao anotacao);
}
