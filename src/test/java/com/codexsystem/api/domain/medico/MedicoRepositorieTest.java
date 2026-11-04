package com.codexsystem.api.domain.medico;

import com.codexsystem.api.domain.enums.Especialidade;
import com.codexsystem.api.domain.enums.Status;
import com.codexsystem.api.domain.model.Consulta;
import com.codexsystem.api.domain.model.Endereco;
import com.codexsystem.api.domain.model.Medico;
import com.codexsystem.api.domain.model.Paciente;
import com.codexsystem.api.domain.repository.MedicoRepositorie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositorieTest {

    @Autowired
    private MedicoRepositorie medicoRepositorie;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria não devolver médico quando o único cadastrado está ocupado na data")
    void escolherMedicoAleatorioLivreNaDataTestCenario1() {
        var proximaSegundaAsDez = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = cadastrarMedico("Dra. Ana", "ana@email.com", Especialidade.CARDIOLOGIA, "12345", dadosEndereco());
        var paciente = cadastrarPaciente("João", "joao@email.com", "12345678900");
        cadastrarConsulta(medico, paciente, proximaSegundaAsDez);
        em.flush();
        em.clear();

        var medicoLivre = medicoRepositorie.escolherMedicoAleatorioLivreNaData(
                Especialidade.CARDIOLOGIA,
                proximaSegundaAsDez,
                Status.ATIVO,
                PageRequest.of(0, 1)
        );

        assertThat(medicoLivre.getContent()).isEmpty();

    }

    @Test
    @DisplayName("Deveria devolver um medico aleatorio livre na data quando ha mais de um medico cadastrado")
    void escolherMedicoAleatorioLivreNaDataTestCenario2() {
        var proximaSegundaAsDez = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medicoOcupado = cadastrarMedico("Dra. Ana", "ana@email.com", Especialidade.CARDIOLOGIA, "12345", dadosEndereco());
        var medicoLivre = cadastrarMedico("Dr. Bruno", "bruno@email.com", Especialidade.CARDIOLOGIA, "54321", dadosEndereco());
        var paciente = cadastrarPaciente("João", "joao@email.com", "12345678900");
        cadastrarConsulta(medicoOcupado, paciente, proximaSegundaAsDez);
        em.flush();
        em.clear();

        var resultado = medicoRepositorie.escolherMedicoAleatorioLivreNaData(
                Especialidade.CARDIOLOGIA,
                proximaSegundaAsDez,
                Status.ATIVO,
                PageRequest.of(0, 1)
        );

        assertThat(resultado.getContent())
                .extracting(Medico::getId)
                .containsExactly(medicoLivre.getId());
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, Especialidade especialidade, String crm, Endereco endereco) {
        var medico = new Medico(nome, email, especialidade, crm, endereco);
        medico.setTelefone("61999999999");
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(nome, "Silva", "61999999999", cpf, email, dadosEndereco());
        em.persist(paciente);
        return paciente;
    }

    private Endereco dadosEndereco() {
        return new Endereco(
                "rua xpto",
                "10",
                null,
                "bairro",
                "Brasilia",
                "DF",
                "00000-000"
        );
    }
}
