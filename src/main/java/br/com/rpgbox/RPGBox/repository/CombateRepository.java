package br.com.rpgbox.RPGBox.repository;

import br.com.rpgbox.RPGBox.entity.Combate;
import br.com.rpgbox.RPGBox.entity.EventoCampanha;
import org.springframework.data.repository.CrudRepository;

public interface CombateRepository extends CrudRepository<Combate, Long> {
}
