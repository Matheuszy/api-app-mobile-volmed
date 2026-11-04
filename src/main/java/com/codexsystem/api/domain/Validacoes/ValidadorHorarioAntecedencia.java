package com.codexsystem.api.domain.Validacoes;

import com.codexsystem.api.domain.dto.request.DadosAgendamentoConsulta;
import com.codexsystem.api.infra.exception.ValidacaoException;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorHorarioAntecedencia {
    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaMinutos < 30) {
            throw new ValidacaoException("A consulta deve ser agendada com pelo menos 30 minutos de antecedência.");
        }
    }
}
