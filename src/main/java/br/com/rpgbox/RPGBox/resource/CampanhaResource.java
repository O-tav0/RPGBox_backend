package br.com.rpgbox.RPGBox.resource;

import br.com.rpgbox.RPGBox.DTO.*;
import br.com.rpgbox.RPGBox.VO.CampanhaVO;
import br.com.rpgbox.RPGBox.VO.RespostaVO;
import br.com.rpgbox.RPGBox.entity.Campanha;
import br.com.rpgbox.RPGBox.entity.Combate;
import br.com.rpgbox.RPGBox.entity.Personagem;
import br.com.rpgbox.RPGBox.service.AnotacaoService;
import br.com.rpgbox.RPGBox.service.CampanhaService;
import br.com.rpgbox.RPGBox.service.CombateService;
import br.com.rpgbox.RPGBox.service.PersonagemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/campanha")
public class CampanhaResource {

    @Autowired
    private CampanhaService campanhaService;

    @Autowired
    private PersonagemService personagemService;

    @Autowired
    private AnotacaoService anotacaoService;

    @Autowired
    private CombateService combateService;

    private RespostaVO respostaRequisicao;

    @PostMapping(path="/novo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "novo", notes = "Incluir nova campanha para um usuário no banco de dados")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 200, message = "Requisição concluída com sucesso"),
            @ApiResponse(code = 400, message = "Problema no processamento")})
    public ResponseEntity<RespostaVO> criarNovaCampanha(@RequestBody CampanhaVO novaCampanha) {
        respostaRequisicao = new RespostaVO();

        try {
            campanhaService.criarNovaCampanha(novaCampanha);
            respostaRequisicao.setMensagem("Campanha criada com sucesso!");
            return ResponseEntity.ok(respostaRequisicao);
        } catch(EntityNotFoundException e) {
            e.printStackTrace();
            respostaRequisicao.setMensagem("Usuário não encontrado na base de dados");
            return ResponseEntity.badRequest().body(respostaRequisicao);
        } catch(Exception e) {
            e.printStackTrace();
            respostaRequisicao.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }

    }

    @GetMapping(path="/{emailUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "novo", notes = "Lista todas as campanhas cadastradas de determinado usuário")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 200, message = "Requisição concluída com sucesso"),
            @ApiResponse(code = 400, message = "Problema no processamento")})
    public ResponseEntity<RespostaVO> criarNovaCampanha(@PathVariable String emailUsuario) {
        respostaRequisicao = new RespostaVO();
        List<CampanhaDTO> campanhasDoUsuario = new ArrayList<CampanhaDTO>();

        try {
            campanhasDoUsuario = campanhaService.buscarCampanhasDoUsuario(emailUsuario);
            respostaRequisicao.setObjetoResposta(campanhasDoUsuario);
            return ResponseEntity.ok(respostaRequisicao);
        } catch(Exception e) {
            e.printStackTrace();
            respostaRequisicao.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }
    }

    @GetMapping(path="/{sqCampanha}/personagens", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "novo", notes = "Busca todos os personagens de uma determinada campanha")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 200, message = "Requisição concluída com sucesso"),
            @ApiResponse(code = 400, message = "Problema no processamento")})
    public ResponseEntity<RespostaVO> recuperarPersonagensDaCampanha(@PathVariable Long sqCampanha) {

        respostaRequisicao = new RespostaVO();

        try {
            List<PersonagemDTO> listaRetorno = montarRetornoPersonagensCampanha(campanhaService.buscarPersonagensDaCampanha(sqCampanha));
            respostaRequisicao.setObjetoResposta(listaRetorno);
            return ResponseEntity.ok(respostaRequisicao);

        }catch(Exception e) {
            e.printStackTrace();
            respostaRequisicao.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }
    }

    @GetMapping(path="/{sqCampanha}/personagens-por-tipo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "novo", notes = "Busca todos os personagens de uma determinada campanha, e os separa pelo tipo")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 200, message = "Requisição concluída com sucesso"),
            @ApiResponse(code = 400, message = "Problema no processamento")})
    public ResponseEntity<RespostaVO> recuperarPersonagensDaCampanhaPorTipo(@PathVariable Long sqCampanha) {

        respostaRequisicao = new RespostaVO();

        try {
            PersonagensCampanhaDTO retorno = campanhaService.buscarPersonagensPorTipo(sqCampanha);
            respostaRequisicao.setObjetoResposta(retorno);
            return ResponseEntity.ok(respostaRequisicao);

        }catch(Exception e) {
            e.printStackTrace();
            respostaRequisicao.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }
    }

    @GetMapping(path="/informacoes-gerais/{sqCampanha}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "novo", notes = "Busca todos os personagens de uma determinada campanha, e os separa pelo tipo")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 200, message = "Requisição concluída com sucesso"),
            @ApiResponse(code = 400, message = "Problema no processamento")})
    public ResponseEntity<RespostaVO> recuperaCampanhaPorSqCampanha(@PathVariable Long sqCampanha) {

        respostaRequisicao = new RespostaVO();
        CampanhaDTO retorno = new CampanhaDTO();

        try {
            Campanha campanha = campanhaService.buscarCampanhaPorId(sqCampanha);
            retorno = campanhaService.converteEmDTO(campanha);
            respostaRequisicao.setObjetoResposta(retorno);
            return ResponseEntity.ok(respostaRequisicao);

        }catch(Exception e) {
            e.printStackTrace();
            respostaRequisicao.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }
    }

    @GetMapping(path="/anotacoes/{sqCampanha}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "novo", notes = "Busca todas as anotações de uma campanha")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 200, message = "Requisição concluída com sucesso"),
            @ApiResponse(code = 400, message = "Problema no processamento")})
    public ResponseEntity<RespostaVO> recuperaAnotacoesPorSqCampanha(@PathVariable Long sqCampanha) {

        respostaRequisicao = new RespostaVO();


        try {
            List<AnotacaoDTO> listaDeAnotacoes = anotacaoService.buscarAnotacoesPorCampanha(sqCampanha);
            respostaRequisicao.setObjetoResposta(listaDeAnotacoes);
            return ResponseEntity.ok(respostaRequisicao);

        }catch(Exception e) {
            e.printStackTrace();
            respostaRequisicao.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }
    }

    @GetMapping(path="/combates/{sqCampanha}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "novo", notes = "Busca todos os combates cadastrados em determinada campanha")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 200, message = "Requisição concluída com sucesso"),
            @ApiResponse(code = 400, message = "Problema no processamento")})
    public ResponseEntity<RespostaVO> buscarCombatesDaCampanha(@PathVariable Long sqCampanha) {

        respostaRequisicao = new RespostaVO();

        try {
           List<CombateDTO> combatesDaCampanha = combateService.buscarCombatesDaCampanha(sqCampanha);
           respostaRequisicao.setObjetoResposta(combatesDaCampanha);
           return ResponseEntity.ok(respostaRequisicao);

        }catch(EntityNotFoundException e) {
            e.printStackTrace();
            System.out.println("Campanha não encontrada com o sqCampanha: " + sqCampanha);
            respostaRequisicao.setMensagem("Campanha não encontrada com o sqCampanha: " + sqCampanha);
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }catch (Exception e) {
            e.printStackTrace();
            respostaRequisicao.setMensagem("Houve um problema ao processar a requisição.");
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }
    }

    public List<PersonagemDTO> montarRetornoPersonagensCampanha(List<Personagem> listaDePersonagens) {
        List<PersonagemDTO> dto = new ArrayList<PersonagemDTO>();

        for(Personagem personagem: listaDePersonagens) {
            dto.add(personagemService.converteEmDTO(personagem));
        }
        return dto;
    }

}
