package com.codexsystem.api.domain.Validacoes;

import com.codexsystem.api.domain.repository.PacienteRepositorie;
import com.codexsystem.api.infra.exception.ValidacaoException;

public class ValidadorPacienteAtivo {

    private PacienteRepositorie pacienteRepositorie;

    public ValidadorPacienteAtivo(PacienteRepositorie pacienteRepositorie) {
        this.pacienteRepositorie = pacienteRepositorie;
    }

    public void validar(Long idPaciente) {
        var pacienteAtivo = pacienteRepositorie.getReferenceById(idPaciente);
        if (!"ATIVO".equals(pacienteAtivo.getStatus())) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico inativo.");
        }
    }

}
