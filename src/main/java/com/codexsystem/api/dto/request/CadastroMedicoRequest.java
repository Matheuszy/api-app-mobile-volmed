package com.codexsystem.api.dto.request;

import com.codexsystem.api.enums.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CadastroMedicoRequest(
    @NotBlank(message = "O nome é obrigatório")
    String nome,
    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O email deve ser válido")
    String email,
    @NotBlank(message = "O telefone é obrigatório")
    String telefone,
    @NotNull(message = "A especialidade é obrigatória")
    Especialidade especialidade,
    @NotBlank(message = "O CRM é obrigatório")
    @Pattern(regexp = "\\d{4,6}", message = "O CRM deve conter apenas números e ter entre 4 e 6 dígitos")
    String crm,
    @NotNull(message = "O endereço é obrigatório")
    @Valid
    EnderecoDto endereco
) {
}
