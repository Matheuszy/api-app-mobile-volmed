package com.codexsystem.api.controller;

import com.codexsystem.api.domain.dto.request.DadosAgendamentoConsulta;
import com.codexsystem.api.domain.dto.response.DadosDetalhamentoConsulta;
import com.codexsystem.api.domain.enums.Especialidade;
import com.codexsystem.api.domain.service.ConsultaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ConsultaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;

    @MockitoBean
    private ConsultaService consultaService;


    @Test
    @DisplayName("Deve devolver codigo 400 quando informacoes invalidas para agendamento de consulta")
    @WithMockUser
    void agendar_cenario1() throws Exception {
        var response = mockMvc.perform(post("/consultas")
                        .with(user("teste").roles("USER")))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    @DisplayName("Deve devolver codigo 200 quando informacoes validas para agendamento de consulta")
    @WithMockUser
    void agendar_cenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especilidade = Especialidade.CARDIOLOGIA;
        when(consultaService.agendarConsulta(any()))
                .thenReturn(new DadosDetalhamentoConsulta(null, 2L, 5L, data));
        var response = mockMvc.perform(post("/consultas")
                        .with(user("teste").roles("USER"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAgendamentoConsultaJson.write(new DadosAgendamentoConsulta(2L, 5L, data, especilidade)).getJson()))
                        .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoConsultaJson.write(
                new DadosDetalhamentoConsulta(null, 2L, 5L, data)).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }


}

