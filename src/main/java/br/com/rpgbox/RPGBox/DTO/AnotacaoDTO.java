package br.com.rpgbox.RPGBox.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnotacaoDTO {
    private String descricaoAnotacao;
    private Long sqAnotacao;
    private String dataAnotacao;
}
