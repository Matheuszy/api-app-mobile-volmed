package com.codexsystem.api.domain.service;

import com.codexsystem.api.domain.model.Usuario;
import com.codexsystem.api.domain.repository.UsuarioRepositorie;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CadastroService {

    private final UsuarioRepositorie repositorie;
    private final PasswordEncoder passwordEncoder;

    public CadastroService(UsuarioRepositorie repositorie, PasswordEncoder passwordEncoder) {
        this.repositorie = repositorie;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void cadastrarUsuario(Usuario usuario) {
        Usuario newUsuario = new Usuario(
        usuario.getLogin(), passwordEncoder.encode(usuario.getSenha())
        );
        repositorie.save(newUsuario);
    }
}
