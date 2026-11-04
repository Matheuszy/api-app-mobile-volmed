package com.codexsystem.api.controller;

import com.codexsystem.api.domain.dto.request.AtualizarPacienteDto;
import com.codexsystem.api.domain.dto.request.CadastrarPacienteRequest;
import com.codexsystem.api.domain.dto.response.CadastroPacienteResponse;
import com.codexsystem.api.domain.service.PacienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/cadastrar")
    public CadastroPacienteResponse criarPaciente(CadastrarPacienteRequest request) {
        return pacienteService.criar(request);
    }

    @GetMapping("/listar")
    public Page<CadastroPacienteResponse> listarPacientes(Pageable pageable) {
        return pacienteService.obterPacientes(pageable);
    }

    @GetMapping("/buscar")
    public CadastroPacienteResponse buscarPacientePorId(Long id) {
        return pacienteService.obterPacientePorId(id);
    }

    @PutMapping("/atualizar")
    public CadastroPacienteResponse atualizarPaciente(@RequestBody AtualizarPacienteDto request) {
        return pacienteService.atualizarPaciente(request);
    }

    @PutMapping("/desativar")
    public void desativarPaciente(Long id) {
        pacienteService.desativarPaciente(id);
    }
}
