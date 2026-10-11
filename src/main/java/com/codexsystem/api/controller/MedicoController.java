package com.codexsystem.api.controller;

import com.codexsystem.api.dto.request.AtualizaMedicoDto;
import com.codexsystem.api.dto.request.CadastroMedicoRequest;
import com.codexsystem.api.dto.response.CadastroMedicoResponseDto;
import com.codexsystem.api.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping("/register/doctor")
    public ResponseEntity<CadastroMedicoResponseDto> registerDoctor(@RequestBody @Valid CadastroMedicoRequest request) {
        CadastroMedicoResponseDto medico = medicoService.cadastrarMedico(request);
        return ResponseEntity.status(201).body(medico);
    }

    @GetMapping("/get/doctor/{id}")
    public ResponseEntity<CadastroMedicoResponseDto> getDoctorById(@PathVariable Long id) {
        CadastroMedicoResponseDto medico = medicoService.obterMedicoPorId(id);
        return ResponseEntity.ok(medico);
    }

    @GetMapping("/get/all/doctors")
    public ResponseEntity<Page<CadastroMedicoResponseDto>> getAllDoctors(
            @PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pageable) {
        Page<CadastroMedicoResponseDto> medicos = medicoService.obterMedicos(pageable);
        return ResponseEntity.status(200).body(medicos);

    }

    @DeleteMapping("/delete/doctor/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        medicoService.excluirMedico(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/doctor")
    public ResponseEntity<CadastroMedicoResponseDto> updateDoctor(@RequestBody AtualizaMedicoDto atualizaMedicoDto) {
        CadastroMedicoResponseDto medico = medicoService.atualizarMedico(atualizaMedicoDto);
        return ResponseEntity.ok(medico);
    }
}
