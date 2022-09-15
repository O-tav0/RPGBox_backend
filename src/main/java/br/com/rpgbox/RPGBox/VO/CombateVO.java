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
public class CombateVO {
    private String tituloCombate;
    private Long sqCampanha;
    private List<PersonagemCombateVO> personagensCombate;
}
