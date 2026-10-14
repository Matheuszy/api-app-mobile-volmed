package com.codexsystem.api.domain.dto.response;

import com.codexsystem.api.domain.enums.Especialidade;

public record CadastroMedicoResponseDto(
    Long idMedico,
    String nome,
    String email,
    Especialidade especialidade
) {
}

