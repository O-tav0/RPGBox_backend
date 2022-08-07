package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.DTO.HabilidadeDTO;
import br.com.rpgbox.RPGBox.entity.Habilidade;
import org.springframework.stereotype.Service;

@Service
public class HabilidadeService {

    public HabilidadeDTO converteEmDTO(Habilidade habilidade) {
        HabilidadeDTO dto = new HabilidadeDTO();

        dto.setNomeHabilidade(habilidade.getNomeHabilidade());
        dto.setTipoHabilidade(habilidade.getTipoHabilidade().getDsTipoHabilidade());
        dto.setNomeHabilidade(habilidade.getNomeHabilidade());
        dto.setDescricaoHabilidade(habilidade.getDescricaoHabilidade());

        return dto;
    }
}
