package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.enums.EnumTipoHabilidade;
import br.com.rpgbox.RPGBox.entity.TipoHabilidade;
import br.com.rpgbox.RPGBox.repository.TipoHabilidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoHabilidadeService {

    @Autowired
    private TipoHabilidadeRepository tipoHabilidadeRepository;

    public TipoHabilidade buscarTipoHabilidade(EnumTipoHabilidade.TipoHabilidadeEnum descricao) {
        EnumTipoHabilidade enumTipo = new EnumTipoHabilidade(descricao);

        return tipoHabilidadeRepository.findById(enumTipo.getCodigoTipoHabilidade().longValue()).get();
    }

}
