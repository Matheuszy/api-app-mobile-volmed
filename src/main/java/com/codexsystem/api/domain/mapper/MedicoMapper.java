package com.codexsystem.api.domain.mapper;

import com.codexsystem.api.domain.dto.request.AtualizaMedicoDto;
import com.codexsystem.api.domain.dto.request.CadastroMedicoRequest;
import com.codexsystem.api.domain.dto.request.EnderecoDto;
import com.codexsystem.api.domain.dto.response.CadastroMedicoResponseDto;
import com.codexsystem.api.domain.model.Endereco;
import com.codexsystem.api.domain.model.Medico;
import org.springframework.stereotype.Component;

@Component
public class MedicoMapper {

    public Medico toEntityMedico(CadastroMedicoRequest dto) {
        Endereco endereco = toEntityEndereco(dto.endereco());
        Medico medico = new Medico(
                dto.nome(),
                dto.email(),
                dto.telefone(),
                dto.especialidade(),
                dto.crm(),
                endereco
        );
        return medico;
    }



    public CadastroMedicoResponseDto toDtoMedico(Medico medico) {
        CadastroMedicoResponseDto dto = new CadastroMedicoResponseDto(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getEspecialidade()
        );
        return dto;
    }

    public AtualizaMedicoDto toDtoAtualizaMedico(Medico medico) {
        AtualizaMedicoDto dto = new AtualizaMedicoDto(
                medico.getId(),
                medico.getNome(),
                medico.getTelefone(),
                medico.getEndereco()
        );
        return dto;
    }

    private Endereco toEntityEndereco(EnderecoDto dto) {
        Endereco endereco = new Endereco(
                dto.logradouro(),
                dto.numero(),
                dto.complemento(),
                dto.bairro(),
                dto.cidade(),
                dto.estado(),
                dto.cep()
        );
        return endereco;
    }


}
