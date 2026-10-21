package com.codexsystem.api.domain.repository;

import com.codexsystem.api.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorie extends JpaRepository<Usuario, Long> {



}
