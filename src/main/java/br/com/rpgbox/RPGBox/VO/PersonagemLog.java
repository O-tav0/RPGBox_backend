package br.com.rpgbox.RPGBox.VO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonagemLog {

    private Long sqPersonagem;
    private String classePersonagem;
    private Integer nivelPersonagem;
    private String nomePersonagem;
    private Integer pontosVida;
    private String racaPersonagem;
    private String tipoPersonagem;
}
