package it.siinfo.springboot2.config;

import jakarta.validation.groups.Default;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig (JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {

        final String[] endPoints = {

                "/index.html",
                "/page2.html",
                "/page3.html"


        };

        http.csrf (customizer -> customizer.disable ())
                .authorizeHttpRequests (request -> request
                .requestMatchers ("/auth/login").permitAll ()
                .requestMatchers ("/auth/register").permitAll ()
                                .requestMatchers (endPoints).permitAll ()
                                .anyRequest ().authenticated ()
                );
//                        .requestMatchers ("/users/lista").permitAll ()
//                .anyRequest ().authenticated ());
//
//        abilita l'autenticazione


//
//        abilita la pagina di login, disabilitandola si creera un pop up di acccesso su broswere
//        http.formLogin (Customizer.withDefaults ());
//
//        per evitare che postman abbia come risposta una pagina http

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
