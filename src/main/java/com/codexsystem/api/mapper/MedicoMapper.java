package com.codexsystem.api.mapper;

import com.codexsystem.api.dto.request.CadastroMedicoRequest;
import com.codexsystem.api.dto.request.EnderecoDto;
import com.codexsystem.api.dto.response.CadastroMedicoResponseDto;
import com.codexsystem.api.model.Endereco;
import com.codexsystem.api.model.Medico;
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

    public CadastroMedicoResponseDto toDtoMedico(Medico medico) {
        CadastroMedicoResponseDto dto = new CadastroMedicoResponseDto(
                medico.getNome(),
                medico.getEmail(),
                medico.getEspecialidade()
        );
        return dto;
    }
}
