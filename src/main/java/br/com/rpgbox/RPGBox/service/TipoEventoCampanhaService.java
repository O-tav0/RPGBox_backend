package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.Enum.EnumTipoEventoCampanha;
import br.com.rpgbox.RPGBox.entity.TipoEventoCampanha;
import br.com.rpgbox.RPGBox.repository.TipoEventoCampanhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoEventoCampanhaService {

    @Autowired
    private TipoEventoCampanhaRepository tipoEventoCampanhaRepository;

    public TipoEventoCampanha buscarTipoEventoCampanha(EnumTipoEventoCampanha.TipoEventoCampanhaEnum descricao) {
        EnumTipoEventoCampanha enumTipo = new EnumTipoEventoCampanha(descricao);

        return tipoEventoCampanhaRepository.findById(enumTipo.getCodigoTipoEvento().longValue()).get();
    }
}
