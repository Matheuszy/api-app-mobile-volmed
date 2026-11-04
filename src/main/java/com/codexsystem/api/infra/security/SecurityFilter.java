package com.codexsystem.api.infra.security;

import com.codexsystem.api.domain.model.Usuario;
import com.codexsystem.api.domain.repository.UsuarioRepositorie;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.http.HttpMethod;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return HttpMethod.OPTIONS.matches(request.getMethod())
                || path.equals("/auth/login")
                || path.equals("/cadastro/user")
                || path.equals("/doctor/register/doctor")
                || path.equals("/pacientes/cadastrar")
                || path.equals("/swagger-ui.html")
                || path.startsWith("/swagger-ui/")
                || path.startsWith("/v3/api-docs/");
    }

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepositorie usuarioRepositorie;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        var tokenJwt = recuperarToken(request);
        if (tokenJwt != null) {
            var tokenValido = tokenService.getSubject(tokenJwt);
            var usuario = usuarioRepositorie.findByLogin(tokenValido).orElseThrow();
            var authentication = new UsernamePasswordAuthenticationToken(usuario,
                    null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var autorizationHeader = request.getHeader("Authorization");
        if (autorizationHeader != null) {
            return autorizationHeader.replace("Bearer ", "");
        }
        return  null;
    }
}
