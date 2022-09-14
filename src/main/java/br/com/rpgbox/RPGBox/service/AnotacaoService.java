package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.DTO.AnotacaoDTO;
import br.com.rpgbox.RPGBox.VO.AnotacaoVO;
import br.com.rpgbox.RPGBox.entity.Anotacao;
import br.com.rpgbox.RPGBox.entity.Campanha;
import br.com.rpgbox.RPGBox.repository.AnotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnotacaoService {

    @Autowired
    private CampanhaService campanhaService;

    @Autowired
    private AnotacaoRepository anotacaoRepository;

    @Autowired
    private EventoCampanhaService eventoCampanhaService;

    public void criarNovaAnotacao(AnotacaoVO novaAnotacao) {

        Campanha campanha = campanhaService.buscarCampanhaPorId(novaAnotacao.getSqCampanha());
        Anotacao anotacao = preencherNovaAnotacao(campanha, novaAnotacao);

        anotacao = anotacaoRepository.save(anotacao);

        salvarEventoCriacaoCampanha(campanha, anotacao);
    }

    public Anotacao preencherNovaAnotacao(Campanha campanha, AnotacaoVO novaAnotacao) {

        Anotacao anotacao = new Anotacao();
        anotacao.setDsAnotacao(novaAnotacao.getAnotacao());
        anotacao.setCampanha(campanha);

        return anotacao;
    }

    public void salvarEventoCriacaoCampanha(Campanha campanha, Anotacao anotacao) {
        eventoCampanhaService.gerarEventoCriacaoAnotacao(campanha, anotacao);
    }

    public List<AnotacaoDTO> buscarAnotacoesPorCampanha(Long sqCampanha) {
        Campanha campanha = campanhaService.buscarCampanhaPorId(sqCampanha);
        List<AnotacaoDTO> listaRetorno = new ArrayList<AnotacaoDTO>();
        List<Anotacao> anotacoesDaCampanha = anotacaoRepository.findAllByCampanha(campanha);

        for(Anotacao anotacao: anotacoesDaCampanha) {
            listaRetorno.add(converteEmDTO(anotacao));
        }

        return listaRetorno;
    }

    public AnotacaoDTO buscarAnotacao(Long sqCampanha) {
        Anotacao anotacao = anotacaoRepository.findById(sqCampanha).get();

        return converteEmDTO(anotacao);
    }

    public AnotacaoDTO converteEmDTO(Anotacao anotacao) {
        AnotacaoDTO dto = new AnotacaoDTO();

        dto.setSqAnotacao(anotacao.getSqAnotacao());
        dto.setDescricaoAnotacao(anotacao.getDsAnotacao());

        return dto;
    }


}
