package br.com.rpgbox.RPGBox.VO;

import br.com.rpgbox.RPGBox.enums.EnumTipoHabilidade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HabilidadeVO {

    private String nomeHabilidade;
    private String descricaoHabilidade;
    private EnumTipoHabilidade.TipoHabilidadeEnum tipoHabilidade;
}
