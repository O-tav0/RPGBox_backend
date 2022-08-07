package br.com.rpgbox.RPGBox.resource;

import br.com.rpgbox.RPGBox.VO.PersonagemVO;
import br.com.rpgbox.RPGBox.VO.RespostaVO;
import br.com.rpgbox.RPGBox.entity.Personagem;
import br.com.rpgbox.RPGBox.service.CampanhaService;
import br.com.rpgbox.RPGBox.service.PersonagemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/personagem")
public class PersonagemResource {

    @Autowired
    private PersonagemService personagemService;

    private RespostaVO respostaRequisicao;

    @PostMapping(path="/novo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "novo", notes = "Incluir novo personagem em uma campanha existente")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 200, message = "Requisição concluída com sucesso"),
            @ApiResponse(code = 400, message = "Problema no processamento")})
    public ResponseEntity<RespostaVO> criarNovoPersonagem(@RequestBody PersonagemVO personagem) {

        respostaRequisicao = new RespostaVO();

        try {
            personagemService.criarNovoPersonagem(personagem);
            respostaRequisicao.setMensagem("Personagem criado com sucesso!");
            return ResponseEntity.ok(respostaRequisicao);
        }catch(Exception e) {
            e.printStackTrace();
            respostaRequisicao.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }
    }

    @GetMapping(path="/{sqPersonagem}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "novo", notes = "Incluir novo personagem em uma campanha existente")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 200, message = "Requisição concluída com sucesso"),
            @ApiResponse(code = 400, message = "Problema no processamento")})
    public ResponseEntity<RespostaVO> buscarPersonagemPorId(@PathVariable Long sqPersonagem) {

        respostaRequisicao = new RespostaVO();

        try {
            Personagem personagem = personagemService.buscarPersonagemPorId(sqPersonagem);
            respostaRequisicao.setObjetoResposta(personagem);
            return ResponseEntity.ok(respostaRequisicao);
        }catch(Exception e) {
            e.printStackTrace();
            respostaRequisicao.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }
    }
}
