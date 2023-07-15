package br.com.rpgbox.RPGBox.repository;

import br.com.rpgbox.RPGBox.entity.Permissao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends CrudRepository<Permissao, Long> {
}
