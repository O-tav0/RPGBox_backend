package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.entity.Usuario;
import br.com.rpgbox.RPGBox.exception.UsuarioJaCadastradoException;
import br.com.rpgbox.RPGBox.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void criarNovoUsuario(Usuario usuario) throws UsuarioJaCadastradoException{
        if(usuarioRepository.findByEmailUsuario(usuario.getEmailUsuario()).isPresent()){
            throw new UsuarioJaCadastradoException();
        } else {
            usuarioRepository.save(usuario);
        }

    }

    public Optional<Usuario> buscarUsuarioPorEmail(String emailUsuario) {
        return usuarioRepository.findByEmailUsuario(emailUsuario);
    }

    public Optional<Usuario> buscarUsuarioPorId(Long sqUsuario) {
        return usuarioRepository.findById(sqUsuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmailUsuario(username);

        if(usuario.isPresent()) {
            Usuario entidade = usuario.get();
            return entidade;
        } else {
            throw new UsernameNotFoundException("Usuario com o e-mail " + username + "não encontrado!");
        }
    }
}
