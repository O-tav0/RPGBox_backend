package br.com.rpgbox.RPGBox.VO.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenVO {
    private String emailUsuario;
    private Boolean isAutenticado;
    private Date criadoEm;
    private Date expiraEm;
    private String accessToken;
    private String refreshToken;
}
