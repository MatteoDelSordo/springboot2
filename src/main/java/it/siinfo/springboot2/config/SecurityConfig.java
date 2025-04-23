package it.siinfo.springboot2.config;

import jakarta.validation.groups.Default;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig (JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

        http.csrf (customizer -> customizer.disable ());
//
//        abilita l'autenticazione
        http.authorizeHttpRequests (request -> request.requestMatchers ("/**").permitAll ().requestMatchers ("/auth" +
                "/**").permitAll ());

//
//        abilita la pagina di login, disabilitandola si creera un pop up di acccesso su broswere
//        http.formLogin (Customizer.withDefaults ());
//
//        per evitare che postman abbia come risposta una pagina http
//        http.httpBasic (Customizer.withDefaults ());
//
//
        http.addFilterBefore (jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement (session -> session.sessionCreationPolicy (SessionCreationPolicy.STATELESS));

        return http.build ();
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration ac) throws Exception {
        return ac.getAuthenticationManager ();
    }


}
