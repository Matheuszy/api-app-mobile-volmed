package com.codexsystem.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity TratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity TratarError400(MethodArgumentNotValidException exception){
        var errors = exception.getFieldErrors();
        var errorsFormatados = errors.stream().map(dadosErro400::new).toList();
        return ResponseEntity.badRequest().body(errorsFormatados);
    }
    private record dadosErro400(String campo, String erro) {
        public dadosErro400(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
