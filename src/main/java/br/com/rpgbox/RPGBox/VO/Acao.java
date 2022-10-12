package br.com.rpgbox.RPGBox.VO;

import br.com.rpgbox.RPGBox.DTO.HabilidadeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Acao {
    private HabilidadeDTO habilidadeUtilizada;
    private PersonagemLog personagemQueUtilizou;
    private PersonagemLog personagemAlvo;
    private Integer danoCausado;
}
