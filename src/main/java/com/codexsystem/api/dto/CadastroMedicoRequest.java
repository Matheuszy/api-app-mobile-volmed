package com.codexsystem.api.dto;

import com.codexsystem.api.enums.Especialidade;

public record CadastroMedicoRequest(
    String nome,
    String email,
    String telefone,
    Especialidade especialidade,
    String crm,

    Endereco endereco
) {
}
