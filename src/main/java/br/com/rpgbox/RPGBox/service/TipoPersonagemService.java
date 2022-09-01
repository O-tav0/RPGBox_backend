package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.enums.TipoDePersonagem;
import br.com.rpgbox.RPGBox.entity.TipoPersonagem;
import br.com.rpgbox.RPGBox.repository.TipoPersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoPersonagemService {

    @Autowired
    private TipoPersonagemRepository tipoPersonagemRepository;

    public TipoPersonagem buscarTipoPersonagem(TipoDePersonagem.TipoPersonagemEnum descricao) {
        TipoDePersonagem enumTipo = new TipoDePersonagem(descricao);

        return tipoPersonagemRepository.findById(enumTipo.getCodigoTipoPersonagem().longValue()).get();
    }
}
