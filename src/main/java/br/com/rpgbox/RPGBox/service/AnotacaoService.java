package br.com.rpgbox.RPGBox.service;

import br.com.rpgbox.RPGBox.DTO.AnotacaoDTO;
import br.com.rpgbox.RPGBox.VO.AnotacaoVO;
import br.com.rpgbox.RPGBox.entity.*;
import br.com.rpgbox.RPGBox.enums.SituacaoDeletadoEnum;
import br.com.rpgbox.RPGBox.repository.AnotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

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

        Collections.sort(anotacoesDaCampanha, new Comparator<Anotacao>() {
            @Override
            public int compare(Anotacao u1, Anotacao u2) {
                return u1.getSqAnotacao().compareTo(u2.getSqAnotacao());
            }
        });

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

    public void alterarAnotacao(Long sqAnotacao, AnotacaoVO anotacaoAtualizada) throws EntityNotFoundException {
        Anotacao anotacao = anotacaoRepository.findById(sqAnotacao).get();

        if(Objects.nonNull(anotacao)) {
            anotacao.setDsAnotacao(anotacaoAtualizada.getAnotacao());
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
