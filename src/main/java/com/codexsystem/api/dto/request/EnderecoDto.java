package com.codexsystem.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDto(
        @NotBlank(message = "O logradouro é obrigatório")
        String logradouro,
        @NotBlank(message = "O número é obrigatório")
        String numero,
        String complemento,
        @NotBlank(message = "O bairro é obrigatório")
        String bairro,
        @NotBlank(message = "A cidade é obrigatória")
        String cidade,
        @NotBlank(message = "O estado é obrigatório")
        String estado,
        @NotBlank(message = "O CEP é obrigatório")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 00000-000")
        String cep
) {
}
