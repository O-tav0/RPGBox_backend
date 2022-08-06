package br.com.rpgbox.RPGBox.DTO;

import br.com.rpgbox.RPGBox.entity.Campanha;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CampanhaDTO extends Campanha {
    private String dataCriacao;
}
