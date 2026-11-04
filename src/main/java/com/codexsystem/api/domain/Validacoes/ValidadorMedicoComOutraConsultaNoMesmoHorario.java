package com.codexsystem.api.domain.Validacoes;

import com.codexsystem.api.domain.dto.request.DadosAgendamentoConsulta;
import com.codexsystem.api.domain.repository.ConsultaRepositorie;
import com.codexsystem.api.infra.exception.ValidacaoException;

public class ValidadorMedicoComOutraConsultaNoMesmoHorario {

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
