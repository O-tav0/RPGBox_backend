package br.com.rpgbox.RPGBox.resource;

import br.com.rpgbox.RPGBox.VO.RespostaVO;
import br.com.rpgbox.RPGBox.entity.Usuario;
import br.com.rpgbox.RPGBox.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    private RespostaVO respostaRequisicao;

    @PostMapping(path="/novo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "novo", notes = "Incluir novo usuário no banco de dados")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 200, message = "Requisição concluída com sucesso") })
    public ResponseEntity<RespostaVO> criarNovoUsuario(@RequestBody Usuario novoUsuario) {
        respostaRequisicao = new RespostaVO();

        try {
            usuarioService.criarNovoUsuario(novoUsuario);
            respostaRequisicao.setMensagem("Usuário inserido com sucesso!");
            return ResponseEntity.ok(respostaRequisicao);
        }catch(Exception e) {
            e.printStackTrace();
            respostaRequisicao.setMensagem("Não conseguimos processar a requisição. Tente novamente mais tarde!");
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }
    }
}
