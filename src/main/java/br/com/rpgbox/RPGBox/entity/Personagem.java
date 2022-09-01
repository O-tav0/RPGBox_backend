package br.com.rpgbox.RPGBox.entity;

import br.com.rpgbox.RPGBox.enums.TipoDePersonagem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_personagem")
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

    @OneToMany(mappedBy = "personagem")
    private List<Habilidade> habilidadesPersonagem;

    public Personagem(Campanha campanha) {
        this.campanha = campanha;
    }

    public Boolean isAventureiro() {
        TipoDePersonagem aventureiro = new TipoDePersonagem(TipoDePersonagem.TipoPersonagemEnum.AVENTUREIRO);
        return this.getTipoPersonagem().getSqTipoPersonagem() == aventureiro.getCodigoTipoPersonagem().longValue();

    }

    public Boolean isNpc() {
        TipoDePersonagem npc = new TipoDePersonagem(TipoDePersonagem.TipoPersonagemEnum.NPC);
        return this.getTipoPersonagem().getSqTipoPersonagem() == npc.getCodigoTipoPersonagem().longValue();

    }

    public Boolean isInimigo() {
        TipoDePersonagem inimigo = new TipoDePersonagem(TipoDePersonagem.TipoPersonagemEnum.INIMIGO);
        return this.getTipoPersonagem().getSqTipoPersonagem() == inimigo.getCodigoTipoPersonagem().longValue();

    }

}
