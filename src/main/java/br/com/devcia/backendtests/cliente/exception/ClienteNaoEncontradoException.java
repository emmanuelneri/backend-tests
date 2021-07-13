package br.com.devcia.backendtests.cliente.exception;

public class ClienteNaoEncontradoException extends RuntimeException {

    public ClienteNaoEncontradoException(String message) {
        super(message);
    }
}
