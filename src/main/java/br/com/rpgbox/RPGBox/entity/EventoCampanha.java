package br.com.rpgbox.RPGBox.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TB_EVENTO_CAMPANHA")
public class EventoCampanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sqEventoCampanha;

    @JoinColumn(name="TP_EVENTO_CAMPANHA")
    @OneToOne
    private TipoEventoCampanha tipoEventoCampanha;

    @JoinColumn(name="SQ_CAMPANHA")
    @OneToOne
    private Campanha campanha;

    @Column(name="DT_EVENTO")
    private Date dataEvento;

    @JoinColumn(name="SQ_PERSONAGEM")
    @OneToOne
    private Personagem personagem;

    @JoinColumn(name="SQ_COMBATE")
    @OneToOne
    private Combate combate;

    @JoinColumn(name="SQ_ANOTACAO")
    @OneToOne
    private Anotacao anotacao;
}
