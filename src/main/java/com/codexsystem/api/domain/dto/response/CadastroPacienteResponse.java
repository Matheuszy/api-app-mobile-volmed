package com.codexsystem.api.domain.dto.response;

public record CadastroPacienteResponse(
    Long idPaciente,
    String nome,
    String email,
    String telefone
) {
}
