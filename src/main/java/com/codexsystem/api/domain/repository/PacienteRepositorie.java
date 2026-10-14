package com.codexsystem.api.domain.repository;

import com.codexsystem.api.domain.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepositorie extends JpaRepository<Paciente, Long> {

}
