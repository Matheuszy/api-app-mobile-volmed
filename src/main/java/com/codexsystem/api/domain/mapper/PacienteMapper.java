package com.codexsystem.api.domain.mapper;

import com.codexsystem.api.domain.dto.request.CadastrarPacienteRequest;
import com.codexsystem.api.domain.dto.request.EnderecoDto;
import com.codexsystem.api.domain.dto.response.CadastroPacienteResponse;
import com.codexsystem.api.domain.model.Endereco;
import com.codexsystem.api.domain.model.Paciente;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {

    public Paciente toEntity(CadastrarPacienteRequest request) {
        return new Paciente(
                request.nome(),
                request.sobrenome(),
                request.telefone(),
                request.cpf(),
                request.email(),
                toEntityEndereco(request.endereco())
        );
    }

    public CadastroPacienteResponse toResponse(Paciente paciente) {
        return new CadastroPacienteResponse(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getTelefone()
        );
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
