package com.codexsystem.api.service;

import com.codexsystem.api.dto.request.AtualizaMedicoDto;
import com.codexsystem.api.dto.request.CadastroMedicoRequest;
import com.codexsystem.api.dto.response.CadastroMedicoResponseDto;
import com.codexsystem.api.enums.StatusMedico;
import com.codexsystem.api.mapper.MedicoMapper;
import com.codexsystem.api.model.Medico;
import com.codexsystem.api.repository.MedicoRepositorie;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    private final MedicoRepositorie medicoRepository;

    private final MedicoMapper mapper;

    public MedicoService(MedicoRepositorie medicoRepository,
                         MedicoMapper mapper) {
        this.medicoRepository = medicoRepository;
        this.mapper = mapper;
    }

    @Transactional
    public CadastroMedicoResponseDto cadastrarMedico(CadastroMedicoRequest medico) {
        Medico newMedico = mapper.toEntityMedico(medico);
        Medico medicoSalvo = medicoRepository.save(newMedico);
        return mapper.toDtoMedico(medicoSalvo);

    }

    public CadastroMedicoResponseDto obterMedicoPorId(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        return mapper.toDtoMedico(medico);
    }

    public Page<CadastroMedicoResponseDto> obterMedicos(Pageable pageable) {
        return medicoRepository.findAll(pageable)
                .map(mapper::toDtoMedico);
    }

    public void desativarMedico(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        medico.setStatus(StatusMedico.INATIVO);
        medicoRepository.save(medico);
    }

    @Transactional
    public CadastroMedicoResponseDto atualizarMedico(AtualizaMedicoDto atualizaMedicoDto) {
        Medico medico = medicoRepository.findById(atualizaMedicoDto.idMedico())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        if (atualizaMedicoDto.nome() != null) {
            medico.setNome(atualizaMedicoDto.nome());
        }
        if (atualizaMedicoDto.telefone() != null) {
            medico.setTelefone(atualizaMedicoDto.telefone());
        }
        if (atualizaMedicoDto.endereco() != null) {
            medico.setEndereco(atualizaMedicoDto.endereco());
        }
        medicoRepository.save(medico);
        return mapper.toDtoMedico(medico);
    }

}
