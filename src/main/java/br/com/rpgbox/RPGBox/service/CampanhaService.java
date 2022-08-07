package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.DTO.CampanhaDTO;
import br.com.rpgbox.RPGBox.VO.CampanhaVO;
import br.com.rpgbox.RPGBox.entity.Campanha;
import br.com.rpgbox.RPGBox.entity.EventoCampanha;
import br.com.rpgbox.RPGBox.entity.Personagem;
import br.com.rpgbox.RPGBox.entity.Usuario;
import br.com.rpgbox.RPGBox.repository.CampanhaRepository;
import br.com.rpgbox.RPGBox.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CampanhaService {

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EventoCampanhaService eventoCampanhaService;

    @Autowired
    private PersonagemRepository personagemRepository;

    public void criarNovaCampanha(CampanhaVO campanhaVO) {

        Usuario usuario = usuarioService.buscarUsuarioPorEmail(campanhaVO.getEmailUsuarioLogado()).orElseThrow(EntityNotFoundException::new);
        Campanha campanhaCriada = preencherObjetoNovaCampanha(campanhaVO, usuario);
        campanhaRepository.save(campanhaCriada);

        inserirEventoCriacaoDaCampanha(campanhaCriada);

    }

    private Campanha preencherObjetoNovaCampanha(CampanhaVO vo, Usuario usuario) {
        Campanha novaCampanha = new Campanha();

        novaCampanha.setTituloCampanha(vo.getTituloCampanha());
        novaCampanha.setDescricaoCampanha(vo.getDescricaoCampanha());
        novaCampanha.setImagemCampanha(vo.getImagemCampanha());
        novaCampanha.setUsuario(usuario);

        return novaCampanha;
    }

    private void inserirEventoCriacaoDaCampanha(Campanha campanha) {
        eventoCampanhaService.gerarEventoCriacaoCampanha(campanha);
    }

    public List<CampanhaDTO> buscarCampanhasDoUsuario(String email) {
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(email).orElseThrow(EntityNotFoundException::new);
        List<Campanha> campanhas = campanhaRepository.findByUsuario(usuario);

        return montarObjetoRetornoListaCampanhas(campanhas);
    }

    public List<CampanhaDTO> montarObjetoRetornoListaCampanhas(List<Campanha> listaDeCampanhas) {
        List<CampanhaDTO> listaResposta = new ArrayList<CampanhaDTO>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for(Campanha campanha: listaDeCampanhas) {
            EventoCampanha eventoCriacao = eventoCampanhaService.buscarEventoCriacaoCampanha(campanha);
            CampanhaDTO dto = new CampanhaDTO();
            dto.setImagemCampanha(campanha.getImagemCampanha());
            dto.setDescricaoCampanha(campanha.getDescricaoCampanha());
            dto.setSqCampanha(campanha.getSqCampanha());
            dto.setUsuario(campanha.getUsuario());
            dto.setTituloCampanha(campanha.getTituloCampanha());
            dto.setDataCriacao(sdf.format(eventoCriacao.getDataEvento()));

            listaResposta.add(dto);
        }

        return listaResposta;
    }

    public Campanha buscarCampanhaPorId(Long sqCampanha) {
        return campanhaRepository.findById(sqCampanha).get();
    }

    public List<Personagem> buscarPersonagensDaCampanha(Long sqCampanha) {
        Campanha campanha = campanhaRepository.findById(sqCampanha).get();

        return personagemRepository.findAllByCampanha(campanha);
    }


}
