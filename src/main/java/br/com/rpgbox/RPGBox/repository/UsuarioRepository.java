package br.com.rpgbox.RPGBox.repository;

import br.com.rpgbox.RPGBox.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository  extends CrudRepository<Usuario, Long> {
}
