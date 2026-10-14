package com.codexsystem.api.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CadastrarPacienteRequest(
        @NotBlank(message = "O nome é obrigatório")
        String nome,
        @NotBlank(message = "O sobrenome é obrigatório")
        String sobrenome,
        @NotBlank(message = "O telefone é obrigatório")
        String telefone,
        @NotBlank(message = "O CPF é obrigatório")
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve estar no formato 000.000.000-00")
        String cpf,
        @NotBlank(message = "O email é obrigatório")
        @Email(message = "O email deve ser válido")
        String email,
        @NotBlank(message = "O sobrenome é obrigatório")
        EnderecoDto endereco
) {
}
