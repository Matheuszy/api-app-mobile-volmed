package com.codexsystem.api.domain.Validacoes.agendamento;

import com.codexsystem.api.domain.dto.request.DadosAgendamentoConsulta;
import com.codexsystem.api.domain.repository.PacienteRepositorie;
import com.codexsystem.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsultas {

    private PacienteRepositorie pacienteRepositorie;

    public ValidadorPacienteAtivo(PacienteRepositorie pacienteRepositorie) {
        this.pacienteRepositorie = pacienteRepositorie;
    }

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteAtivo = pacienteRepositorie.getReferenceById(dados.idPaciente());
        if (!"ATIVO".equals(pacienteAtivo.getStatus())) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico inativo.");
        }
    }

}
