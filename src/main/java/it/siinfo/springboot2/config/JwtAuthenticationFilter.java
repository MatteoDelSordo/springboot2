package it.siinfo.springboot2.config;

import it.siinfo.springboot2.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter (JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal (@NotNull HttpServletRequest request,
                                     @NotNull HttpServletResponse response,
                                     @NotNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader ("Authrization");
        final String jwtToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith ("Bearer ")) {
            filterChain.doFilter (request, response);
            return;
        }
        jwtToken = authHeader.substring (7);
        username = jwtService.extractUsername(jwtToken);
    }


}
