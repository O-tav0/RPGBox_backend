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
@Table(name="TB_PERSONAGEM")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sqPersonagem;

    @Column(name="DS_NOME_PERSONAGEM")
    private String nomePersonagem;

    @Column(name="DS_RACA")
    private String racaPersonagem;

    @Column(name="DS_CLASSE")
    private String classePersonagem;

    @JoinColumn(name="SQ_CAMPANHA")
    @OneToOne
    private Campanha campanha;

    @Column(name="NR_PONTOS_VIDA")
    private Integer pontosDeVida;

    @Column(name="DS_IMAGEM")
    private String imagemPersonagem;

    @JoinColumn(name="TP_PERSONAGEM")
    @OneToOne
    private TipoPersonagem tipoPersonagem;

    @Column(name="NR_NIVEL_PERSONAGEM")
    private Integer nivelPersonagem;

}
