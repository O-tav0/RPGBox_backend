package br.com.rpgbox.RPGBox.repository;

import br.com.rpgbox.RPGBox.entity.Campanha;
import br.com.rpgbox.RPGBox.entity.EventoCampanha;
import br.com.rpgbox.RPGBox.entity.Personagem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonagemRepository extends CrudRepository<Personagem, Long> {

    public List<Personagem> findAllByCampanhaAndStDeletado(Campanha campanha, String stDeletado);
}
