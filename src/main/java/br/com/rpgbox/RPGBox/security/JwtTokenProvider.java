package br.com.rpgbox.RPGBox.security;

import br.com.rpgbox.RPGBox.VO.security.TokenVO;
import br.com.rpgbox.RPGBox.exception.AutenticacaoJWTInvalidaException;
import br.com.rpgbox.RPGBox.service.UsuarioService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey = "53cr37";

    @Value("${security.jwt.token.expire-length}")
    private Long tempoTokenEmMilissegundos = 3600000L;

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algoritmo = null;

    public JwtTokenProvider(UsuarioService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algoritmo = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenVO createAccessToken(String emailUsuario, List<String> roles) {
        Date hoje = new Date();
        Date validade = new Date(hoje.getTime() + tempoTokenEmMilissegundos);
        String accessToken = recuperarAccessToken(emailUsuario, roles, hoje, validade);
        String refreshToken = criarRefreshToken(emailUsuario, roles, hoje, validade);

        return new TokenVO(emailUsuario, true, hoje, validade, accessToken, refreshToken);
    }

    public String recuperarAccessToken(String emailUsuario, List<String> roles, Date hoje, Date validade) {

        String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(hoje)
                .withExpiresAt(validade)
                .withSubject(emailUsuario)
                .withIssuer(issuerUrl)
                .sign(algoritmo)
                .strip();

    }

    public String criarRefreshToken(String emailUsuario, List<String> roles, Date hoje, Date validade) {
        String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        Date validadeRefreshToken = new Date(hoje.getTime() + (tempoTokenEmMilissegundos * 3));

        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(hoje)
                .withExpiresAt(validadeRefreshToken)
                .withSubject(emailUsuario)
                .sign(algoritmo)
                .strip();
    }

    public Authentication recuperarAutenticacao(String token) {
        DecodedJWT decodedJwt = decodedToken(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJwt.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public TokenVO refreshToken(String refreshToken) {
        if (refreshToken.contains("Bearer ")) refreshToken =
                refreshToken.substring("Bearer ".length());

        JWTVerifier verifier = JWT.require(algoritmo).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);
        String username = decodedJWT.getSubject();
        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
        return createAccessToken(username, roles);
    }

    private DecodedJWT decodedToken(String token) {

        Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(alg).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        return decodedJWT;
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");

        if(Objects.nonNull(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }

        return null;
    }

    public Boolean validarToken(String token) {
        DecodedJWT decodedJWT = decodedToken(token);
        try {
            if(decodedJWT.getExpiresAt().before(new Date())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new AutenticacaoJWTInvalidaException("Token JWT inválido ou expirado enviado!");
        }
    }
}
