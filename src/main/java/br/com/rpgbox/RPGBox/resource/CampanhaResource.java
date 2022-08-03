package br.com.rpgbox.RPGBox.resource;

import br.com.rpgbox.RPGBox.VO.CampanhaVO;
import br.com.rpgbox.RPGBox.VO.RespostaVO;
import br.com.rpgbox.RPGBox.entity.Campanha;
import br.com.rpgbox.RPGBox.entity.Usuario;
import br.com.rpgbox.RPGBox.exception.UsuarioJaCadastradoException;
import br.com.rpgbox.RPGBox.service.CampanhaService;
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
        List<Campanha> campanhasDoUsuario = new ArrayList<Campanha>();

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
}
