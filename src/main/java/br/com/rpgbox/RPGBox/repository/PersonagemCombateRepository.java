package br.com.rpgbox.RPGBox.repository;

import br.com.rpgbox.RPGBox.entity.EventoCampanha;
import br.com.rpgbox.RPGBox.entity.PersonagemCombate;
import org.springframework.data.repository.CrudRepository;

public interface PersonagemCombateRepository extends CrudRepository<PersonagemCombate, Long> {
}
