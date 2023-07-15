package br.com.rpgbox.RPGBox.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="TB_USUARIO")
@Entity
public class Usuario implements UserDetails, Serializable {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long sqUsuario;

      @Column(name="DS_NOME_USUARIO")
      private String nomeUsuario;

      @Column(name="DS_EMAIL")
      private String emailUsuario;

      @Column(name="DS_SENHA")
      private String senhaUsuario;

      @Column(name="DS_CAMINHO_IMAGEM")
      private String caminhoImagem;

      @Column(name="ST_CONTA_NAO_EXPIRADA")
      private Boolean contaNaoExpirada;

      @Column(name="ST_CONTA_NAO_BLOQUEADA")
      private Boolean contaNaoBloqueada;

      @Column(name="ST_CREDENCIAIS_NAO_BLOQUEADAS")
      private Boolean credenciaisNaoExpiradas;

      @Column(name="ST_ATIVADO")
      private Boolean isHabilitado;

      @ManyToMany(fetch = FetchType.EAGER)
      @JoinTable(name = "tb_permissao_usuario", joinColumns = {@JoinColumn (name = "sq_usuario")},
              inverseJoinColumns = {@JoinColumn (name = "sq_permissao")}
      )
      private List<Permissao> permissoes;

      public List<String> getRoles() {
            List<String> roles = new ArrayList<>();
            for (Permissao permissao : permissoes) {
                  roles.add(permissao.getDescricao());
            }
            return roles;
      }

      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
            return this.permissoes;
      }

      @Override
      public String getPassword() {
            return this.senhaUsuario;
      }

      @Override
      public String getUsername() {
            return this.emailUsuario;
      }

      @Override
      public boolean isAccountNonExpired() {
            return this.contaNaoExpirada;
      }

      @Override
      public boolean isAccountNonLocked() {
            return this.contaNaoBloqueada;
      }

      @Override
      public boolean isCredentialsNonExpired() {
            return this.credenciaisNaoExpiradas;
      }

      @Override
      public boolean isEnabled() {
            return this.isHabilitado;
      }
}
