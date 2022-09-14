package br.com.rpgbox.RPGBox.resource;

import br.com.rpgbox.RPGBox.DTO.AnotacaoDTO;
import br.com.rpgbox.RPGBox.VO.AnotacaoVO;
import br.com.rpgbox.RPGBox.VO.CampanhaVO;
import br.com.rpgbox.RPGBox.VO.RespostaVO;
import br.com.rpgbox.RPGBox.service.AnotacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/anotacao")
public class AnotacaoResource {

    @Autowired
    private AnotacaoService anotacaoService;

    @PostMapping(path="/novo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "novo", notes = "Incluir nova anotacao em uma campanha")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 200, message = "Requisição concluída com sucesso"),
            @ApiResponse(code = 400, message = "Problema no processamento")})
    public ResponseEntity<RespostaVO> criarNovaCampanha(@RequestBody AnotacaoVO novaAnotacao) {

        RespostaVO respostaRequisicao = new RespostaVO();

        try {
            anotacaoService.criarNovaAnotacao(novaAnotacao);
            respostaRequisicao.setMensagem("Anotação adicionada com sucesso!");
            return ResponseEntity.ok(respostaRequisicao);
        } catch (Exception e) {
            respostaRequisicao.setMensagem("Houve um problema ao inserir a anotação.");
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }

    }

    @GetMapping (path="/{sqAnotacao}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "novo", notes = "Buscar informações específicas de determinada anotação")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 200, message = "Requisição concluída com sucesso"),
            @ApiResponse(code = 400, message = "Problema no processamento")})
    public ResponseEntity<RespostaVO> criarNovaCampanha(@PathVariable Long sqAnotacao) {

        RespostaVO respostaRequisicao = new RespostaVO();

        try {
            AnotacaoDTO dto = anotacaoService.buscarAnotacao(sqAnotacao);
            respostaRequisicao.setObjetoResposta(dto);
            return ResponseEntity.ok(respostaRequisicao);
        } catch (Exception e) {
            respostaRequisicao.setMensagem("Houve um problema ao buscar a anotação.");
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }

    }
}
