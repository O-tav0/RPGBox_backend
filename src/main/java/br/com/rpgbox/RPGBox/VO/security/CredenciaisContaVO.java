package br.com.rpgbox.RPGBox.VO.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CredenciaisContaVO implements Serializable {
    private String username;
    private String password;
}
