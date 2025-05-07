package it.siinfo.springboot2.config;

import it.siinfo.springboot2.service.JwtService;
import it.siinfo.springboot2.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private UserService userService;
    private HandlerExceptionResolver handlerExceptionResolver;

    public JwtAuthenticationFilter (JwtService jwtService,
                                    UserService userService,
                                    @Qualifier("handlerExceptionResolver") HandlerExceptionResolver handlerExceptionResolver) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal (@NotNull HttpServletRequest request,
                                     @NotNull HttpServletResponse response,
                                     @NotNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader ("Authorization");
        final String jwtToken;
        final String username;

        if (authHeader == null || !authHeader.startsWith ("Bearer ")) {
            filterChain.doFilter (request, response);
            return;
        }

        try {
            jwtToken = authHeader.substring (7);
            username = jwtService.extractUsername (jwtToken);
            if (jwtService.validateToken (jwtToken)) {
                Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
                if (authentication == null && username != null) {
                    addUserToAuthenticationContext (request, username);
                }
                filterChain.doFilter (request, response);
            } else {
                SecurityContextHolder.clearContext ();
                filterChain.doFilter (request, response);
            }
        } catch (Exception e) {
            handlerExceptionResolver.resolveException (request,response,null,e);
            System.out.println ("errore nel chatch filterchain " + e);

        }


    }

    private void addUserToAuthenticationContext (HttpServletRequest request,
                                                 String username) {
        UserDetails userDetails = userService.loadUserByUsername (username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken (userDetails,
                null,
                userDetails.getAuthorities ());
        token.setDetails (new WebAuthenticationDetailsSource ().buildDetails (request));
        SecurityContextHolder.getContext ().setAuthentication (token);


    }

}
