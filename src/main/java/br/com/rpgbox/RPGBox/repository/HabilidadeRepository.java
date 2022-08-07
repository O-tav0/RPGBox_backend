package br.com.rpgbox.RPGBox.repository;

import br.com.rpgbox.RPGBox.entity.EventoCampanha;
import br.com.rpgbox.RPGBox.entity.Habilidade;
import org.springframework.data.repository.CrudRepository;

public interface HabilidadeRepository extends CrudRepository<Habilidade, Long> {
}
