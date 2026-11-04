package com.codexsystem.api.domain.Validacoes.agendamento;

import com.codexsystem.api.domain.dto.request.DadosAgendamentoConsulta;
import com.codexsystem.api.domain.repository.ConsultaRepositorie;
import com.codexsystem.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoConsultas {

    private final ConsultaRepositorie consultaRepositorie;

    public ValidadorPacienteSemOutraConsultaNoDia(ConsultaRepositorie consultaRepositorie) {
        this.consultaRepositorie = consultaRepositorie;
    }

    public void validar(DadosAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().withHour(7).withMinute(0).withSecond(0).withNano(0);
        var ultimoHorario = dados.data().withHour(18).withMinute(0).withSecond(0).withNano(0);

        var pacientePossuiOutraConsultaNoDia = consultaRepositorie.existsByPacienteIdAndData(dados.idPaciente(),primeiroHorario, ultimoHorario);

        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia!");
        }
    }
}
