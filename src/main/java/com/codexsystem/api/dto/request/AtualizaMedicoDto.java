package com.codexsystem.api.dto.request;

import com.codexsystem.api.model.Endereco;

public record AtualizaMedicoDto(
        Long idMedico,
        String nome,

        String telefone,

        Endereco endereco
) {
}
