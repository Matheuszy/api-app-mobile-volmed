package com.codexsystem.api.domain.repository;

import com.codexsystem.api.domain.enums.Status;
import com.codexsystem.api.domain.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepositorie extends JpaRepository<Medico, Long> {

    @Query("SELECT m FROM Medico m WHERE m.status = :status")
    Page<Medico> findByStatus(Status status, Pageable pageable);

}
