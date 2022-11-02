package br.com.rpgbox.RPGBox.repository;

import br.com.rpgbox.RPGBox.entity.Combate;
import br.com.rpgbox.RPGBox.entity.EventoCampanha;
import br.com.rpgbox.RPGBox.entity.PersonagemCombate;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface PersonagemCombateRepository extends CrudRepository<PersonagemCombate, Long> {

    @Transactional
    public void deleteAllByCombate(Combate combate);
}
