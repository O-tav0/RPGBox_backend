package br.com.rpgbox.RPGBox.VO;

import br.com.rpgbox.RPGBox.entity.Campanha;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CampanhaVO {

    private String tituloCampanha;
    private String imagemCampanha;
    private String descricaoCampanha;
    private String emailUsuarioLogado;
}
