package br.com.rpgbox.RPGBox.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="tb_habilidade")
public class Habilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sqHabilidade;

    @Column(name="DS_NOME_HABILIDADE")
    private String nomeHabilidade;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="SQ_PERSONAGEM")
    private Personagem personagem;

    @JoinColumn(name="TP_HABILIDADE")
    @OneToOne
    private TipoHabilidade tipoHabilidade;

    @Column(name="DS_HABILIDADE")
    private String descricaoHabilidade;
}
