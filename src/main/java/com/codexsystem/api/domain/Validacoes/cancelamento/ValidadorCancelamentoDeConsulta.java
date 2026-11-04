package com.codexsystem.api.domain.Validacoes.cancelamento;

import com.codexsystem.api.domain.dto.request.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsulta dados);

}
