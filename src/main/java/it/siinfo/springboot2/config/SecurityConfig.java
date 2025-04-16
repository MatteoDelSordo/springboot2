//package it.siinfo.springboot2.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//
//
//public class SecurityConfig {
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
//
//////
////        http.csrf (customizer -> customizer.disable ());
////
////        abilita l'autenticazione
////        http.authorizeHttpRequests (request -> request.anyRequest ().authenticated ());
////
////        abilita la pagina di login, disabilitandola si creera un pop up di acccesso su broswere
////        http.formLogin (Customizer.withDefaults ());
////
////        per evitare che postman abbia come risposta una pagina http
////        http.httpBasic (Customizer.withDefaults ());
////
////
////        http.sessionManagement (session -> session.sessionCreationPolicy (SessionCreationPolicy.STATELESS));
//
//        return http.build ();
//    }
//
//
//}
