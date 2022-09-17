package br.com.rpgbox.RPGBox.resource;

import br.com.rpgbox.RPGBox.VO.CombateVO;
import br.com.rpgbox.RPGBox.VO.RespostaVO;
import br.com.rpgbox.RPGBox.service.CombateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
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

}