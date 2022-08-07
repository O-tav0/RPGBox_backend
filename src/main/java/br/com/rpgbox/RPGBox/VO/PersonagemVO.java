package br.com.rpgbox.RPGBox.VO;

import br.com.rpgbox.RPGBox.enums.EnumTipoPersonagem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonagemVO {

    private String nomePersonagem;
    private String racaPersonagem;
    private String classePersonagem;
    private Long sqCampanha;
    private Integer pontosVida;
    private String imagem;
    private EnumTipoPersonagem.TipoPersonagemEnum tipoPersonagem;
    private Integer nivelPersonagem;
    private List<HabilidadeVO> habilidadesPersonagem;
}
