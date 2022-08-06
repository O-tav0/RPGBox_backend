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
@Table(name="TB_ANOTACAO")
public class Anotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sqAnotacao;

    @JoinColumn(name="SQ_CAMPANHA")
    @OneToOne
    private Campanha campanha;

    @Column(name="DS_ANOTACAO")
    private String dsAnotacao;

}
