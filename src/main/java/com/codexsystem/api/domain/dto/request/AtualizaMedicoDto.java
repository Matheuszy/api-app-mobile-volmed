package com.codexsystem.api.domain.dto.request;

import com.codexsystem.api.domain.model.Endereco;

public record AtualizaMedicoDto(
        Long idMedico,
        String nome,
        String telefone,

        Endereco endereco
) {
}
