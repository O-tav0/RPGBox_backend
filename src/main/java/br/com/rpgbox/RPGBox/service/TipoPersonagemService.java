package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.Enum.EnumTipoEventoCampanha;
import br.com.rpgbox.RPGBox.Enum.EnumTipoPersonagem;
import br.com.rpgbox.RPGBox.entity.TipoEventoCampanha;
import br.com.rpgbox.RPGBox.entity.TipoPersonagem;
import br.com.rpgbox.RPGBox.repository.TipoEventoCampanhaRepository;
import br.com.rpgbox.RPGBox.repository.TipoPersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TipoPersonagemService {

    @Autowired
    private TipoPersonagemRepository tipoPersonagemRepository;

    public TipoPersonagem buscarTipoPersonagem(EnumTipoPersonagem.TipoPersonagemEnum descricao) {
        EnumTipoPersonagem enumTipo = new EnumTipoPersonagem(descricao);

        return tipoPersonagemRepository.findById(enumTipo.getCodigoTipoPersonagem().longValue()).get();
    }
}
