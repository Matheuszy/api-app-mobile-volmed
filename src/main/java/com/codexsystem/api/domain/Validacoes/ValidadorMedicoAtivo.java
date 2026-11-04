package com.codexsystem.api.domain.Validacoes;

import com.codexsystem.api.domain.repository.MedicoRepositorie;
import com.codexsystem.api.infra.exception.ValidacaoException;

public class ValidadorMedicoAtivo {

    private final MedicoRepositorie medicoRepository;

    public ValidadorMedicoAtivo(MedicoRepositorie medicoRepository) {
        this.medicoRepository = medicoRepository;
    }
    public void validar(Long idMedico) {
       var medico  = medicoRepository.getReferenceById(idMedico);

        if (!"ATIVO".equals(medico.getStatus())) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico inativo.");
        }

    }
}
