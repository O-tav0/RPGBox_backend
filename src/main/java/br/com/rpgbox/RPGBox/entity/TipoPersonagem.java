package br.com.rpgbox.RPGBox.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TB_TIPO_PERSONAGEM")
public class TipoPersonagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sqTipoPersonagem;

    @Column(name="DS_TIPO_PERSONAGEM")
    private String dsTipoPersonagem;
}
