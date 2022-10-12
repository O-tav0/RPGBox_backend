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
public class Turno {
    private Integer numeroTurno;
    private List<Acao> acoesDoTurno;
}
