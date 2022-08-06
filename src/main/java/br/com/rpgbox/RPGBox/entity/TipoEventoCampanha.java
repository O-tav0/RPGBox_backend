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
@Table(name="TB_TIPO_EVENTO_CAMPANHA")
public class TipoEventoCampanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sqTipoEventoCampanha;

    @Column(name="DS_TIPO_EVENTO_CAMPANHA")
    private String descricaoEventoCampanha;

}
