package br.com.rpgbox.RPGBox.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonagemCombateDTO {
    private PersonagemDTO personagem;
    private Long nrOrdemCombate;
}
