package br.com.rpgbox.RPGBox.DTO;

import br.com.rpgbox.RPGBox.entity.Anotacao;
import br.com.rpgbox.RPGBox.entity.Combate;
import br.com.rpgbox.RPGBox.entity.EventoCampanha;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class EventoCampanhaDTO {
    private Long sqEventoCampanha;
    private String descricaoEventoCampanha;
    private String dataEvento;
    private PersonagemDTO personagem;
    private Combate combate;
    private AnotacaoDTO anotacao;

}
