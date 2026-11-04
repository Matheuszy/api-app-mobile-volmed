package com.codexsystem.api.domain.Validacoes.agendamento;

import com.codexsystem.api.domain.dto.request.DadosAgendamentoConsulta;
import com.codexsystem.api.domain.repository.ConsultaRepositorie;
import com.codexsystem.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoConsultas {

    private final ConsultaRepositorie consultaRepositorie;

    public ValidadorMedicoComOutraConsultaNoMesmoHorario(ConsultaRepositorie consultaRepositorie) {
        this.consultaRepositorie = consultaRepositorie;
    }

    public void validar(DadosAgendamentoConsulta dados) {
        var medicoPossuiOutraConsultaNoDia = consultaRepositorie.existsByMedicoAndData(dados.idMedico(), dados.data());

        if (medicoPossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Médico já possui uma consulta agendada para este dia.");
        }
    }
}
