package br.com.rpgbox.RPGBox.DTO;

import br.com.rpgbox.RPGBox.VO.CombateLog;
import br.com.rpgbox.RPGBox.entity.StatusCombate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CombateDTO {

    private Long sqCombate;
    private String dtCombate;
    private String statusCombate;
    private String tituloCombate;
    private List<PersonagemCombateDTO> personagensDoCombate;
    private CombateLog resumoCombate;
}
