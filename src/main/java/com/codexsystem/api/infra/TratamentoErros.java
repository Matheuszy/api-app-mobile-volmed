package com.codexsystem.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity TratarError404(){
        return ResponseEntity.notFound().build();
    }
}
