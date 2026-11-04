package com.codexsystem.api.domain.Validacoes.cancelamento;

import com.codexsystem.api.domain.dto.request.DadosCancelamentoConsulta;
import com.codexsystem.api.domain.repository.ConsultaRepositorie;
import com.codexsystem.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeConsulta {

    private final ConsultaRepositorie repository;

    public ValidadorHorarioAntecedencia(ConsultaRepositorie repository) {
        this.repository = repository;
    }

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}
