package com.codexsystem.api.domain.repository;

import com.codexsystem.api.domain.model.Consulta;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepositorie extends JpaRepository<Consulta, Long> {

    boolean existsByMedicoAndData(Long idMedico, LocalDateTime data);

    boolean existsByPacienteIdAndData(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
