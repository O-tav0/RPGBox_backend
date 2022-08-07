package br.com.rpgbox.RPGBox.DTO;

import br.com.rpgbox.RPGBox.entity.Campanha;
import br.com.rpgbox.RPGBox.entity.Habilidade;
import br.com.rpgbox.RPGBox.entity.Personagem;
import br.com.rpgbox.RPGBox.entity.TipoPersonagem;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PersonagemDTO {

    private Long sqPersonagem;
    private String nomePersonagem;
    private String racaPersonagem;
    private String classePersonagem;
    private Integer pontosDeVida;
    private String imagemPersonagem;
    private String tipoPersonagem;
    private Integer nivelPersonagem;
    private List<HabilidadeDTO> habilidadesPersonagem;

}
