package br.com.rpgbox.RPGBox.repository;

import br.com.rpgbox.RPGBox.entity.Campanha;
import br.com.rpgbox.RPGBox.entity.Combate;
import br.com.rpgbox.RPGBox.entity.EventoCampanha;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CombateRepository extends CrudRepository<Combate, Long> {

    public List<Combate> findAllByCampanha(Campanha campanha);
}
