package com.codexsystem.api.domain.dto.response;

import com.codexsystem.api.domain.model.Consulta;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
    Long idConsulta,
    Long idMedico,
    Long idPaciente,

    LocalDateTime dateTime
) {
    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(
            consulta.getId(),
            consulta.getMedico().getId(),
            consulta.getPaciente().getId(),
            consulta.getData()
        );
    }
}
