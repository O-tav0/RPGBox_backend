package br.com.rpgbox.RPGBox.resource;


import br.com.rpgbox.RPGBox.DTO.CampanhaDTO;
import br.com.rpgbox.RPGBox.VO.RespostaVO;
import br.com.rpgbox.RPGBox.service.EventoCampanhaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/eventos")
public class EventoCampanhaResource {

    private RespostaVO respostaRequisicao;

    @Autowired
    private EventoCampanhaService eventoCampanhaService;

    @GetMapping(path="/{sqCampanha}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "novo", notes = "Busca o histórico de eventos que aconteceram em determinada campanha")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Erro interno"),
            @ApiResponse(code = 200, message = "Requisição concluída com sucesso"),
            @ApiResponse(code = 400, message = "Problema no processamento")})
    public ResponseEntity<RespostaVO> buscarHistoricoCampanha(@PathVariable Long sqCampanha) {

        respostaRequisicao = new RespostaVO();


        try {
            respostaRequisicao.setObjetoResposta(eventoCampanhaService.buscarHistoricoCampanha(sqCampanha));
            return ResponseEntity.ok(respostaRequisicao);
        } catch(Exception e) {
            e.printStackTrace();
            respostaRequisicao.setMensagem(e.getMessage());
            return ResponseEntity.badRequest().body(respostaRequisicao);
        }
    }

}
