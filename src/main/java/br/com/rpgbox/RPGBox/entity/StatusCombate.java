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
@Table(name="TB_STATUS_COMBATE")
public class StatusCombate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sqStatusCombate;

    @Column(name="DS_STATUS_COMBATE")
    private String dsStatusCombate;

}
