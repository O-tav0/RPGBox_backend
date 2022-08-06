package br.com.rpgbox.RPGBox.repository;

import br.com.rpgbox.RPGBox.entity.StatusCombate;
import br.com.rpgbox.RPGBox.entity.TipoEventoCampanha;
import org.springframework.data.repository.CrudRepository;

public interface StatusCombateRepository extends CrudRepository<StatusCombate, Long> {
}
