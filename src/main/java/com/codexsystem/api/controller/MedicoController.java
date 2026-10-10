package com.codexsystem.api.controller;

import com.codexsystem.api.dto.CadastroMedicoRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class MedicoController {

    @PostMapping("/register/doctor")
    public void registerDoctor(@RequestBody CadastroMedicoRequest request) {
        // Implement the logic to register a doctor
    }
}
