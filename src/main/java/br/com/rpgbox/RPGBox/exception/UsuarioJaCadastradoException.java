package br.com.rpgbox.RPGBox.exception;

public class UsuarioJaCadastradoException extends Exception {

    public UsuarioJaCadastradoException() {
        super("Usuário já cadastrado na base de dados.");
    }

}
