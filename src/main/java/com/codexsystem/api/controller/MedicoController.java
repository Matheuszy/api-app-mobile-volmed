package com.codexsystem.api.controller;

import com.codexsystem.api.dto.request.CadastroMedicoRequest;
import com.codexsystem.api.dto.response.CadastroMedicoResponseDto;
import com.codexsystem.api.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/delete/doctor/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        medicoService.excluirMedico(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/doctor/{id}")
    public ResponseEntity<CadastroMedicoResponseDto> updateDoctor(@PathVariable Long id, @RequestBody String nome) {
        CadastroMedicoResponseDto medico = medicoService.atualizarMedico(id, nome);
        return ResponseEntity.ok(medico);
    }
}
