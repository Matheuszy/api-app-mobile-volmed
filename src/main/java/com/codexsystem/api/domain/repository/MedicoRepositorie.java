package com.codexsystem.api.domain.repository;

import com.codexsystem.api.domain.enums.Especialidade;
import com.codexsystem.api.domain.enums.Status;
import com.codexsystem.api.domain.model.Medico;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MedicoRepositorie extends JpaRepository<Medico, Long> {

    @Query("SELECT m FROM Medico m WHERE m.status = 'ATIVO'")
    Page<Medico> findByStatus(Status status, Pageable pageable);

    @Query("SELECT m FROM Medico m WHERE m.status = 'ATIVO' AND m.especialidade = :especialidade AND m.id NOT IN (SELECT c.medico.id FROM Consulta c WHERE c.data = :data) ORDER BY RAND() limit 1")
    Medico  escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);
}
