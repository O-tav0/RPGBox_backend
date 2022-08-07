package br.com.rpgbox.RPGBox.repository;

import br.com.rpgbox.RPGBox.entity.Anotacao;
import br.com.rpgbox.RPGBox.entity.EventoCampanha;
import org.springframework.data.repository.CrudRepository;

public interface AnotacaoRepository extends CrudRepository<Anotacao, Long> {
}
