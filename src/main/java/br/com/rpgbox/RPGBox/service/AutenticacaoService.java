package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.VO.security.CredenciaisContaVO;
import br.com.rpgbox.RPGBox.VO.security.TokenVO;
import br.com.rpgbox.RPGBox.entity.Usuario;
import br.com.rpgbox.RPGBox.repository.UsuarioRepository;
import br.com.rpgbox.RPGBox.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AutenticacaoService {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity login(CredenciaisContaVO vo) {
        try {
            String username = vo.getUsername();
            String password = vo.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            Optional<Usuario> usuario = usuarioRepository.findByEmailUsuario(username);

            TokenVO token = new TokenVO();
            if(usuario.isPresent()) {
                token = tokenProvider.createAccessToken(username, usuario.get().getRoles());
            } else {
               throw new UsernameNotFoundException("Usuário não encontrado com esse e-mail");
            }
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied! : " + e.getMessage());
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken) {
        var user = usuarioRepository.findByEmailUsuario(username);

        var tokenResponse = new TokenVO();
        if (user.isPresent()) {
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
        return ResponseEntity.ok(tokenResponse);
    }
}
