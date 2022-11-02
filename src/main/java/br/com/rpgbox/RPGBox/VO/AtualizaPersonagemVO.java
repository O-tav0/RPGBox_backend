package br.com.rpgbox.RPGBox.VO;

import br.com.rpgbox.RPGBox.enums.TipoDePersonagem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizaPersonagemVO {

    private String nomePersonagem;
    private String racaPersonagem;
    private String classePersonagem;
    private Integer pontosVida;
    private String imagem;
    private TipoDePersonagem.TipoPersonagemEnum tipoPersonagem;
    private Integer nivelPersonagem;
    private List<HabilidadeVO> habilidadesPersonagem;
}
