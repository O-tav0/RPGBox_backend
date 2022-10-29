package br.com.rpgbox.RPGBox.repository;

import br.com.rpgbox.RPGBox.entity.Campanha;
import br.com.rpgbox.RPGBox.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CampanhaRepository extends CrudRepository<Campanha, Long> {

    public List<Campanha> findByUsuarioAndStDeletado(Usuario usuario, String stDeletado);
}
