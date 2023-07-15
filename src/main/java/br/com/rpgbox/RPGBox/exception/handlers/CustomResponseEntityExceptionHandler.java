package br.com.rpgbox.RPGBox.exception.handlers;

import br.com.rpgbox.RPGBox.exception.AutenticacaoJWTInvalidaException;
import br.com.rpgbox.RPGBox.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AutenticacaoJWTInvalidaException.class)
    public final ResponseEntity<ExceptionResponse> tratarAuthException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), new Date());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
    }
}