package com.codexsystem.api.domain.dto.request;

public record AtualizarPacienteDto (
    Long idPaciente,
    String nome,
    String telefone,
    EnderecoDto endereco
) {
}
