package br.com.rpgbox.RPGBox.exception;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private String msg;
    private Date data;
}
