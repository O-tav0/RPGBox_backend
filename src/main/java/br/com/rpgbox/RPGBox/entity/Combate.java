package br.com.rpgbox.RPGBox.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TB_COMBATE")
public class Combate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sqCombate;

    @Column(name="DT_COMBATE")
    private Date dtCombate;

    @Column(name="DS_RESUMO_COMBATE")
    private String resumoCombate;

    @JoinColumn(name="SQ_CAMPANHA")
    @OneToOne
    private Campanha campanha;

    @JoinColumn(name="ST_COMBATE")
    @OneToOne
    private StatusCombate statusCombate;

    @Column(name="ds_titulo_combate")
    private String tituloCombate;

    @Column(name="ST_DELETADO")
    private String stDeletado;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "combate")
    private List<PersonagemCombate> personagensDoCombate;

    public Combate(Campanha campanha) {
        this.campanha = campanha;
    }
}
