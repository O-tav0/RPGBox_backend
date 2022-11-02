package br.com.rpgbox.RPGBox.repository;

import br.com.rpgbox.RPGBox.entity.EventoCampanha;
import br.com.rpgbox.RPGBox.entity.Habilidade;
import br.com.rpgbox.RPGBox.entity.Personagem;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface HabilidadeRepository extends CrudRepository<Habilidade, Long> {

    @Transactional
    public void deleteAllByPersonagem(Personagem personagem);
}
