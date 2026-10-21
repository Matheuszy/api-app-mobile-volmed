package com.codexsystem.api.domain.service;

import com.codexsystem.api.domain.repository.UsuarioRepositorie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepositorie repositorie;
    public AutenticacaoService(UsuarioRepositorie repositorie) {
        this.repositorie = repositorie;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repositorie.findByLogin(username);
    }
}
