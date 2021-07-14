package br.com.devcia.backendtests;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<Erro> handleConflict(ConstraintViolationException cvex) {
        return ResponseEntity.badRequest().body(new Erro(cvex.getMessage()));
    }

    public record Erro(String mensagem) {
    }

}
