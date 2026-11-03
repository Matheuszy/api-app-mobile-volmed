package com.codexsystem.api.controller;

import com.codexsystem.api.domain.dto.request.DadosAgendamentoConsulta;
import com.codexsystem.api.domain.dto.response.DadosDetalhamentoConsulta;
import com.codexsystem.api.domain.service.ConsultaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        consultaService.agendarConsulta(dados);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(null, null, null, null));
    }

}