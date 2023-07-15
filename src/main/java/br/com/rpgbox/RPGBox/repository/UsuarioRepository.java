package br.com.rpgbox.RPGBox.repository;

import br.com.rpgbox.RPGBox.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UsuarioRepository  extends CrudRepository<Usuario, Long> {

    public Optional<Usuario> findByEmailUsuario(String email);
}
