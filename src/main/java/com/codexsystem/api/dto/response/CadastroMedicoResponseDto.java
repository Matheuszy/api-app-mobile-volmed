package com.codexsystem.api.dto.response;

import com.codexsystem.api.enums.Especialidade;

public record CadastroMedicoResponseDto(
    Long idMedico,
    String nome,
    String email,
    Especialidade especialidade
) {
}

