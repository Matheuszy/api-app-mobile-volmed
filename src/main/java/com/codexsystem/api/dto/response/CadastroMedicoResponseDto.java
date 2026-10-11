package com.codexsystem.api.dto.response;

import com.codexsystem.api.enums.Especialidade;

public record CadastroMedicoResponseDto(
    String nome,
    String email,
    Especialidade especialidade
) {
}

