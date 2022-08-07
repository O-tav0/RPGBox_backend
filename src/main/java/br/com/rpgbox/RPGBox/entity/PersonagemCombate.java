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
@Table(name="TB_PERSONAGEM_COMBATE")
public class PersonagemCombate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sqPersonagemCombate;

    @JoinColumn(name="SQ_PERSONAGEM")
    @OneToOne
    private Personagem personagem;

    @ManyToOne
    private Combate sqCombate;
}
