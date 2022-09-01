package br.com.rpgbox.RPGBox.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonagensCampanhaDTO {

    private List<PersonagemDTO> aventureiros;
    private List<PersonagemDTO> npcs;
    private List<PersonagemDTO> inimigos;
}
