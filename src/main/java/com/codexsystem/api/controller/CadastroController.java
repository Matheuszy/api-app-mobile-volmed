package com.codexsystem.api.controller;

import com.codexsystem.api.domain.model.Usuario;
import com.codexsystem.api.domain.service.CadastroService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    private final CadastroService cadastroService;

    public CadastroController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }

    @PostMapping("/user")
    public void cadastrarUsuario(@RequestBody Usuario usuario) {
        cadastroService.cadastrarUsuario(usuario);
    }

}
