package com.codexsystem.api.domain.service;

import com.codexsystem.api.domain.Validacoes.agendamento.ValidadorAgendamentoConsultas;
import com.codexsystem.api.domain.Validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import com.codexsystem.api.domain.enums.Status;
import com.codexsystem.api.domain.dto.request.DadosAgendamentoConsulta;
import com.codexsystem.api.domain.dto.request.DadosCancelamentoConsulta;
import com.codexsystem.api.domain.dto.response.DadosDetalhamentoConsulta;
import com.codexsystem.api.domain.model.Consulta;
import com.codexsystem.api.domain.model.Medico;
import com.codexsystem.api.domain.repository.ConsultaRepositorie;
import com.codexsystem.api.domain.repository.MedicoRepositorie;
import com.codexsystem.api.domain.repository.PacienteRepositorie;
import com.codexsystem.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepositorie consultaRepository;
    private final MedicoRepositorie medicoRepository;

    private final List<ValidadorAgendamentoConsultas> validadores;

    private final List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;
    private final PacienteRepositorie pacienteRepository;

    public ConsultaService(ConsultaRepositorie consultaRepository,
                           MedicoRepositorie medicoRepository,
                           PacienteRepositorie pacienteRepository,
                           List<ValidadorCancelamentoDeConsulta> validadoresCancelamento,
                           List<ValidadorAgendamentoConsultas> validadores) {
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.validadoresCancelamento = validadoresCancelamento;
        this.validadores = validadores;
    }

    public DadosDetalhamentoConsulta agendarConsulta(DadosAgendamentoConsulta dados) {

        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Paciente não encontrado");
        }
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Médico não encontrado");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);

        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data");
        }

        var consulta = new Consulta(
                medico,
                paciente,
                dados.data()
        );
        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando o médico não for escolhido");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(
                        dados.especialidade(), dados.data(), Status.ATIVO, PageRequest.of(0, 1))
                .stream()
                .findFirst()
                .orElse(null);
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}
