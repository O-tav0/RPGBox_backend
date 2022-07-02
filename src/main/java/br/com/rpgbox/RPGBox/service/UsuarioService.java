package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.entity.Usuario;
import br.com.rpgbox.RPGBox.exception.UsuarioJaCadastradoException;
import br.com.rpgbox.RPGBox.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void criarNovoUsuario(Usuario usuario) throws UsuarioJaCadastradoException{
        if(usuarioRepository.findByEmailUsuario(usuario.getEmailUsuario()).isPresent()){
            throw new UsuarioJaCadastradoException();
        } else {
            usuarioRepository.save(usuario);
        }

    }
}
