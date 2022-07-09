package br.com.rpgbox.RPGBox.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="TB_USUARIO")
@Entity
public class Usuario {

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
}
