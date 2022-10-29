package br.com.rpgbox.RPGBox.repository;

import br.com.rpgbox.RPGBox.entity.Anotacao;
import br.com.rpgbox.RPGBox.entity.Campanha;
import br.com.rpgbox.RPGBox.entity.EventoCampanha;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnotacaoRepository extends CrudRepository<Anotacao, Long> {

    List<Anotacao> findAllByCampanhaAndStDeletado(Campanha campanha, String stDeletado);
}
