package br.com.rpgbox.RPGBox.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizaCombateVO {

    private String tituloCombate;
    private List<PersonagemCombateVO> personagensCombate;
}
