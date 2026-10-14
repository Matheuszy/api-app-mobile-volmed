package com.codexsystem.api.domain.service;

import com.codexsystem.api.domain.dto.request.AtualizaMedicoDto;
import com.codexsystem.api.domain.dto.request.CadastrarPacienteRequest;
import com.codexsystem.api.domain.dto.response.CadastroMedicoResponseDto;
import com.codexsystem.api.domain.dto.response.CadastroPacienteResponse;
import com.codexsystem.api.domain.enums.Status;
import com.codexsystem.api.domain.mapper.PacienteMapper;
import com.codexsystem.api.domain.model.Paciente;
import com.codexsystem.api.domain.repository.PacienteRepositorie;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    private final PacienteRepositorie repositorie;
    private final PacienteMapper mapper;

    public PacienteService(PacienteRepositorie repositorie, PacienteMapper mapper) {
        this.repositorie = repositorie;
        this.mapper = mapper;
    }

    @Transactional
    public CadastroPacienteResponse toDtoMedico(CadastrarPacienteRequest paciente) {
        if (repositorie.findByNome(paciente.nome()).isPresent()) {
            throw new RuntimeException("Paciente já cadastrado");
        }
        Paciente newPaciente = mapper.toEntity(paciente);
        Paciente savedPaciente = repositorie.save(newPaciente);
        return mapper.toResponse(savedPaciente);
    }

    public Page<CadastroPacienteResponse> obterPacientes(Pageable pageable) {
        return repositorie.findAll(pageable)
                .map(mapper::toResponse);
    }

    public CadastroPacienteResponse obterPacientePorId(Long id) {
        return repositorie.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }
    public void desativarPaciente(Long id) {
        Paciente paciente = repositorie.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        paciente.setStatus(Status.INATIVO);
        repositorie.save(paciente);
    }

    public CadastroPacienteResponse atualizarPaciente(AtualizaMedicoDto paciente) {
        Paciente existingPaciente = repositorie.findById(paciente.idMedico())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        if (paciente.nome() != null) {
            existingPaciente.setNome(paciente.nome());
        }
        if (paciente.telefone() != null) {
            existingPaciente.setTelefone(paciente.telefone());
        }
        if (paciente.endereco() != null) {
            existingPaciente.setEndereco(paciente.endereco());
        }

        Paciente updatedPaciente = repositorie.save(existingPaciente);
        return mapper.toResponse(updatedPaciente);
    }

}
