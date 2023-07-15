package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.entity.Permissao;
import br.com.rpgbox.RPGBox.entity.Usuario;
import br.com.rpgbox.RPGBox.enums.EnumPermissoes;
import br.com.rpgbox.RPGBox.exception.UsuarioJaCadastradoException;
import br.com.rpgbox.RPGBox.repository.PermissaoRepository;
import br.com.rpgbox.RPGBox.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PermissaoRepository permissaoRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void criarNovoUsuario(Usuario usuario) throws UsuarioJaCadastradoException{
        if(usuarioRepository.findByEmailUsuario(usuario.getEmailUsuario()).isPresent()){
            throw new UsuarioJaCadastradoException();
        } else {
            usuario.setSenhaUsuario(criptografarSenha(usuario.getSenhaUsuario()));
            usuario.setContaNaoBloqueada(true);
            usuario.setIsHabilitado(true);
            usuario.setContaNaoExpirada(true);
            usuario.setCredenciaisNaoExpiradas(true);
            usuario.setPermissoes(getPermissaoComum());

            usuarioRepository.save(usuario);
        }

    }

    private List<Permissao> getPermissaoComum() {
        List<Permissao> permissoes = new ArrayList<>();
        permissoes.add(permissaoRepository.findById(EnumPermissoes.COMMOM_USER.getCodigo()).get());
        return permissoes;
    }

    private String criptografarSenha(String senhaPlain) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(new Pbkdf2PasswordEncoder());

        String result = passwordEncoder.encode(senhaPlain);
        result = result.replace("{pbkdf2}", "");
        return result;
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
