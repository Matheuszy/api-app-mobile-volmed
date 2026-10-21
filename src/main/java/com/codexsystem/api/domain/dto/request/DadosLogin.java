package com.codexsystem.api.domain.dto.request;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(
        @NotBlank(message = "O login é obrigatório")
        String login,
        @NotBlank(message = "A senha é obrigatória")
        String senha
) {
}
