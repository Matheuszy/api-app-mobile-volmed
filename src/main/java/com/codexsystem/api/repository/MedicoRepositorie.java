package com.codexsystem.api.repository;

import com.codexsystem.api.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepositorie extends JpaRepository<Medico, Long> {

}
