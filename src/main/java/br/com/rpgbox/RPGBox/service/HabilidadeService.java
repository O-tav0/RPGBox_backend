package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.DTO.HabilidadeDTO;
import br.com.rpgbox.RPGBox.entity.Habilidade;
import br.com.rpgbox.RPGBox.entity.Personagem;
import br.com.rpgbox.RPGBox.repository.HabilidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabilidadeService {

    @Autowired
    private HabilidadeRepository habilidadeRepository;

    public HabilidadeDTO converteEmDTO(Habilidade habilidade) {
        HabilidadeDTO dto = new HabilidadeDTO();

        dto.setNomeHabilidade(habilidade.getNomeHabilidade());
        dto.setTipoHabilidade(habilidade.getTipoHabilidade().getDsTipoHabilidade());
        dto.setNomeHabilidade(habilidade.getNomeHabilidade());
        dto.setDescricaoHabilidade(habilidade.getDescricaoHabilidade());

        return dto;
    }

    public void deletarHabilidadesPersonagem(Personagem personagem) {
        habilidadeRepository.deleteAllByPersonagem(personagem);
    }
}
