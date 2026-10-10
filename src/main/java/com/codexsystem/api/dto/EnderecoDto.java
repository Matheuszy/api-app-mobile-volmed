package com.codexsystem.api.dto;

public record EnderecoDto(
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
) {
}
