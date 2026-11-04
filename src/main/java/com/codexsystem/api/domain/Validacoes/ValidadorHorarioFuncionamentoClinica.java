package com.codexsystem.api.domain.Validacoes;

import com.codexsystem.api.domain.dto.request.DadosAgendamentoConsulta;
import com.codexsystem.api.infra.exception.ValidacaoException;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamentoClinica {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var diaDaSemana = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horaDaAberturaClinica = dataConsulta.getHour() < 7;
        var horaDoFechamentoClinica = dataConsulta.getHour() > 18;

        if (diaDaSemana || horaDaAberturaClinica || horaDoFechamentoClinica){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }

    }

}
