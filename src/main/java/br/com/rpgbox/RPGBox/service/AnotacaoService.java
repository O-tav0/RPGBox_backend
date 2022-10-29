package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.DTO.AnotacaoDTO;
import br.com.rpgbox.RPGBox.VO.AnotacaoVO;
import br.com.rpgbox.RPGBox.entity.Anotacao;
import br.com.rpgbox.RPGBox.entity.Campanha;
import br.com.rpgbox.RPGBox.entity.Combate;
import br.com.rpgbox.RPGBox.entity.EventoCampanha;
import br.com.rpgbox.RPGBox.enums.SituacaoDeletadoEnum;
import br.com.rpgbox.RPGBox.repository.AnotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        salvarEventoCriacaoAnotacao(campanha, anotacao);
    }

    public Anotacao preencherNovaAnotacao(Campanha campanha, AnotacaoVO novaAnotacao) {

        Anotacao anotacao = new Anotacao();
        anotacao.setDsAnotacao(novaAnotacao.getAnotacao());
        anotacao.setCampanha(campanha);

        return anotacao;
    }

    public void salvarEventoCriacaoAnotacao(Campanha campanha, Anotacao anotacao) {
        eventoCampanhaService.gerarEventoCriacaoAnotacao(campanha, anotacao);
    }

    public List<AnotacaoDTO> buscarAnotacoesPorCampanha(Long sqCampanha) {
        Campanha campanha = campanhaService.buscarCampanhaPorId(sqCampanha);

        List<AnotacaoDTO> listaRetorno = new ArrayList<AnotacaoDTO>();
        List<Anotacao> anotacoesDaCampanha = anotacaoRepository.findAllByCampanhaAndStDeletado(campanha, null);

        for(Anotacao anotacao: anotacoesDaCampanha) {
            listaRetorno.add(converteEmDTO(anotacao));
        }

        return listaRetorno;
    }

    public AnotacaoDTO buscarAnotacao(Long sqAnotacao) throws EntityNotFoundException {
        Anotacao anotacao = anotacaoRepository.findById(sqAnotacao).get();
        if(Objects.nonNull(anotacao)) {
            return converteEmDTO(anotacao);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void deletarAnotacao(Long sqAnotacao) throws EntityNotFoundException {
        Anotacao anotacao = anotacaoRepository.findById(sqAnotacao).get();
        SituacaoDeletadoEnum situacao = new SituacaoDeletadoEnum(SituacaoDeletadoEnum.SituacaoEnum.SIM);

        if(Objects.nonNull(anotacao)) {
            anotacao.setStDeletado(situacao.getSituacao());
            anotacaoRepository.save(anotacao);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public AnotacaoDTO converteEmDTO(Anotacao anotacao) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        EventoCampanha evento = eventoCampanhaService.buscarEventoAnotacao(anotacao);
        AnotacaoDTO dto = new AnotacaoDTO();

        dto.setSqAnotacao(anotacao.getSqAnotacao());
        dto.setDescricaoAnotacao(anotacao.getDsAnotacao());
        dto.setDataAnotacao(sdf.format(evento.getDataEvento()));

        return dto;
    }


}
