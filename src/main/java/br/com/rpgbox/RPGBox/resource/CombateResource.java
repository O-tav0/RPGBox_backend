package br.com.rpgbox.RPGBox.resource;

import br.com.rpgbox.RPGBox.DTO.CombateDTO;
import br.com.rpgbox.RPGBox.VO.*;
import br.com.rpgbox.RPGBox.service.CombateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/combates")
public class CombateResource {

    @Autowired
    private CombateService combateService;

    @PostMapping(path="/novo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "novo", notes = "Adiciona um novo combate na campanha")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 200, message = "Requisição concluída com sucesso"),
            @ApiResponse(code = 400, message = "Problema no processamento")})
    public ResponseEntity<RespostaVO> buscarHistoricoCampanha(@RequestBody CombateVO novoCombate) {

        RespostaVO respostaRequisicao = new RespostaVO();

        try {
            combateService.criarNovoCombate(novoCombate);
            respostaRequisicao.setMensagem("Combate criado com sucesso!");
            return ResponseEntity.ok(respostaRequisicao);

        } catch(Exception e) {
            e.printStackTrace();
            respostaRequisicao.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }
    }

    @GetMapping(path="/{sqCombate}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "novo", notes = "Adiciona um novo combate na campanha")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 200, message = "Requisição concluída com sucesso"),
            @ApiResponse(code = 400, message = "Problema no processamento")})
    public ResponseEntity<RespostaVO> buscarPersonagensDoCombate(@PathVariable Long sqCombate) {

        RespostaVO respostaRequisicao = new RespostaVO();

        try {
            CombateDTO dto = combateService.buscarCombate(sqCombate);
            respostaRequisicao.setObjetoResposta(dto);
            return ResponseEntity.ok(respostaRequisicao);
        } catch(EntityNotFoundException etf) {
            etf.printStackTrace();
            respostaRequisicao.setMensagem("Combate não encontrado!");
            return ResponseEntity.badRequest().body(respostaRequisicao);
        } catch(Exception e) {
            e.printStackTrace();
            respostaRequisicao.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }
    }

    @PutMapping(path="/{sqCombate}/finalizar", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "novo", notes = "Finaliza e atualiza o status de um combate na campanha")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 200, message = "Requisição concluída com sucesso"),
            @ApiResponse(code = 400, message = "Problema no processamento")})
    public ResponseEntity<RespostaVO> finalizarCombate(@PathVariable Long sqCombate, @RequestBody CombateLog resumoCombate) {

        RespostaVO respostaRequisicao = new RespostaVO();

        try {
            combateService.finalizarCombate(sqCombate, resumoCombate);
            respostaRequisicao.setMensagem("Combate finalizado com sucesso!");
            return ResponseEntity.ok(respostaRequisicao);

        } catch(Exception e) {
            e.printStackTrace();
            respostaRequisicao.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }
    }

    @DeleteMapping(path="/{sqCombate}/deletar", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "novo", notes = "Realiza uma exclusão lógica de um registro de combate através do campo ST_DELETADO")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 200, message = "Registro deletado"),
            @ApiResponse(code = 400, message = "Problema no processamento")})
    public ResponseEntity<RespostaVO> deletarCombate(@PathVariable Long sqCombate) {

        RespostaVO respostaRequisicao = new RespostaVO();

        try {
            combateService.deletarCombate(sqCombate);
            respostaRequisicao.setMensagem("Combate deletada com sucesso!");
            return ResponseEntity.ok(respostaRequisicao);

        }catch(EntityNotFoundException e) {
            e.printStackTrace();
            respostaRequisicao.setMensagem("Combate não encontrada com o sqCombate: " + sqCombate);
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }catch (Exception e) {
            e.printStackTrace();
            respostaRequisicao.setMensagem("Houve um problema ao processar a requisição.");
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }
    }

    @PutMapping(path="/{sqCombate}/atualizar", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "novo", notes = "Atualiza o Registro de um combate")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 200, message = "Requisição concluída com sucesso"),
            @ApiResponse(code = 400, message = "Problema no processamento")})
    public ResponseEntity<RespostaVO> AtualizarCampanha(@PathVariable Long sqCombate, @RequestBody AtualizaCombateVO combateAtualizado) {

        RespostaVO respostaRequisicao = new RespostaVO();

        try {
            combateService.atualizarCombate(sqCombate, combateAtualizado);
            respostaRequisicao.setMensagem("Combate atualizado com sucesso!");
            return ResponseEntity.ok(respostaRequisicao);

        } catch (Exception e) {
            e.printStackTrace();
            respostaRequisicao.setMensagem("Houve um problema ao processar a requisição.");
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }
    }

}
