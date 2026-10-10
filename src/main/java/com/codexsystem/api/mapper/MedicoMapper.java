package com.codexsystem.api.mapper;

import com.codexsystem.api.dto.CadastroMedicoRequest;
import com.codexsystem.api.dto.EnderecoDto;
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

    public CadastroMedicoRequest toDtoMedico(Medico medico) {
        EnderecoDto enderecoDto = toDtoEndereco(medico.getEndereco());
        CadastroMedicoRequest dto = new CadastroMedicoRequest(
                medico.getNome(),
                medico.getEmail(),
                medico.getTelefone(),
                medico.getEspecialidade(),
                medico.getCrm(),
                enderecoDto
        );
        return dto;
    }

    private EnderecoDto toDtoEndereco(Endereco endereco) {
        EnderecoDto dto = new EnderecoDto(
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep()
        );
        return dto;
    }
}
