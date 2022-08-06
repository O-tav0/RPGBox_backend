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
@Table(name="TB_TIPO_HABILIDADE")
public class TipoHabilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sqTipoHabilidade;

    @Column(name="DS_TIPO_HABILIDADE")
    private String dsTipoHabilidade;
}
