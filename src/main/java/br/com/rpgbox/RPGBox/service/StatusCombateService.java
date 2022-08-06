package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.Enum.EnumStatusCombate;
import br.com.rpgbox.RPGBox.Enum.EnumTipoHabilidade;
import br.com.rpgbox.RPGBox.entity.StatusCombate;
import br.com.rpgbox.RPGBox.entity.TipoHabilidade;
import br.com.rpgbox.RPGBox.repository.StatusCombateRepository;
import br.com.rpgbox.RPGBox.repository.TipoHabilidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusCombateService {

    @Autowired
    private StatusCombateRepository statusRepository;

    public StatusCombate buscarStatusCombate(EnumStatusCombate.StatusCombateEnum descricao) {
        EnumStatusCombate enumTipo = new EnumStatusCombate(descricao);

        return statusRepository.findById(enumTipo.getCodigoStatusCombate().longValue()).get();
    }

}
