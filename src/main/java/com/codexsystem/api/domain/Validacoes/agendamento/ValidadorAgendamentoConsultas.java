package com.codexsystem.api.domain.Validacoes.agendamento;

import com.codexsystem.api.domain.dto.request.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoConsultas {
    void validar(DadosAgendamentoConsulta dados);
}
