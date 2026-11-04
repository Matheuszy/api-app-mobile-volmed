package com.codexsystem.api.domain.Validacoes;

import com.codexsystem.api.domain.dto.request.DadosAgendamentoConsulta;
import com.codexsystem.api.domain.repository.MedicoRepositorie;
import com.codexsystem.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsultas {
    private final MedicoRepositorie medicoRepository;

    public ValidadorMedicoAtivo(MedicoRepositorie medicoRepository) {
        this.medicoRepository = medicoRepository;
    }
    public void validar(DadosAgendamentoConsulta dados) {
       var medico  = medicoRepository.getReferenceById(dados.idMedico());

        if (!"ATIVO".equals(medico.getStatus())) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico inativo.");
        }

    }
}
