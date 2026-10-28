package com.codexsystem.api.domain.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
    Long idConsulta,
    Long idMedico,
    Long idPaciente,

    LocalDateTime dateTime
) {
}
