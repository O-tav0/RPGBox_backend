package br.com.rpgbox.RPGBox.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="TB_PERMISSAO")
@Entity
public class Permissao implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sqPermissao;

    @Column(name="DS_PERMISSAO")
    private String descricao;


    @Override
    public String getAuthority() {
        return this.descricao;
    }
}
