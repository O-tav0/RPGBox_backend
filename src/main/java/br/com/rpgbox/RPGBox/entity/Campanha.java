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
@Table(name="TB_CAMPANHA")
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sqCampanha;

    @Column(name="DS_TITULO_CAMPANHA")
    private String tituloCampanha;

    @Column(name="DS_IMAGEM_CAMPANHA")
    private String imagemCampanha;

    @Column(name="DS_CAMPANHA")
    private String descricaoCampanha;

    @JoinColumn(name="SQ_USUARIO")
    @OneToOne
    private Usuario usuario;

    @Column(name="ST_DELETADO")
    private String stDeletado;
}
