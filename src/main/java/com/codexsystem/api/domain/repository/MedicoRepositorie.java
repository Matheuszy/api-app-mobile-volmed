package com.codexsystem.api.domain.repository;

import com.codexsystem.api.domain.enums.Especialidade;
import com.codexsystem.api.domain.enums.Status;
import com.codexsystem.api.domain.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepositorie extends JpaRepository<Medico, Long> {

    @Query("SELECT m FROM Medico m WHERE m.status = :status")
    Page<Medico> findByStatus(@Param("status") Status status, Pageable pageable);

    @Query("""
            select m from Medico m
            where
            m.status = :status
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.medico.id from Consulta c
                where
                c.data = :data
        and
                c.motivoCancelamento is null
            )
            order by function('rand')
""")
    Page<Medico> escolherMedicoAleatorioLivreNaData(
            @Param("especialidade") Especialidade especialidade,
            @Param("data") LocalDateTime data,
            @Param("status") Status status,
            Pageable pageable
    );
}
