package br.com.rpgbox.RPGBox.repository;

import br.com.rpgbox.RPGBox.entity.TipoEventoCampanha;
import br.com.rpgbox.RPGBox.entity.TipoPersonagem;
import org.springframework.data.repository.CrudRepository;

public interface TipoPersonagemRepository extends CrudRepository<TipoPersonagem, Long> {

}
