package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.VO.CampanhaVO;
import br.com.rpgbox.RPGBox.entity.Campanha;
import br.com.rpgbox.RPGBox.entity.Usuario;
import br.com.rpgbox.RPGBox.repository.CampanhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CampanhaService {

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Autowired
    private UsuarioService usuarioService;

    public void criarNovaCampanha(CampanhaVO campanhaVO) {

        Campanha novaCampanha = new Campanha();
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(campanhaVO.getEmailUsuarioLogado()).orElseThrow(EntityNotFoundException::new);

        novaCampanha.setTituloCampanha(campanhaVO.getTituloCampanha());
        novaCampanha.setDescricaoCampanha(campanhaVO.getDescricaoCampanha());
        novaCampanha.setImagemCampanha(campanhaVO.getImagemCampanha());

        novaCampanha.setUsuario(usuario);

        campanhaRepository.save(novaCampanha);
    }

    public List<Campanha> buscarCampanhasDoUsuario(String email) {
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(email).orElseThrow(EntityNotFoundException::new);
        return campanhaRepository.findByUsuario(usuario);
    }
}
