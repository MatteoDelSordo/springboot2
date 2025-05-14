package it.siinfo.springboot2.config;

import it.siinfo.springboot2.dto.RefreshTokenDto;
import it.siinfo.springboot2.service.JwtService;
import it.siinfo.springboot2.service.RefreshTokenService;
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
    private RefreshTokenService refreshTokenService;

    public JwtAuthenticationFilter (JwtService jwtService,
                                    UserService userService,
                                    @Qualifier("handlerExceptionResolver") HandlerExceptionResolver handlerExceptionResolver,
                                    RefreshTokenService refreshTokenService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
//    Questo metodo viene chiamato ogni volta che avviene una chiamata al server
    protected void doFilterInternal (@NotNull HttpServletRequest request,
                                     @NotNull HttpServletResponse response,
                                     @NotNull FilterChain filterChain) throws ServletException, IOException {
//        Qui viene controllato se l'header Authorization è presente nella chiamata
        final String authHeader = request.getHeader ("Authorization");
        final String jwtToken;
        final String username;
// Questo blocco serve a controllare se l'header è popolato || se è del tipo giusto se non lo è l'utente viene
// "buttato fuori", ovvero non è piu autorizzato a fare la chiamata e ricevera un errore 403 o 401(più probabile)
        if (authHeader == null || !authHeader.startsWith ("Bearer ")) {
            filterChain.doFilter (request, response);
            return;
        }
//se il controllo precedente è andato a buon fine viene prelevato il token, viene rimossa la parte iniziale con
// substring(i primi 7 caratteri incluso lo spazio)
        try {
            jwtToken = authHeader.substring (7);
//            viene estratto l'username tramite il service dedicato ai jwt
            username = jwtService.extractUsername (jwtToken);


            RefreshTokenDto refreshTokenDto = refreshTokenService.findByUserEmail (username);

            String refreshToken = refreshTokenDto.getToken ();


//            questo blocco valida il token estratto sempre tramite il service
            if (jwtService.validateToken (jwtToken) && jwtService.validateToken (refreshToken)) {
//            Qui viene presa l'authentication dal securityContext che è contenuto nel securityContextHolder
                Authentication authentication = SecurityContextHolder.getContext ().getAuthentication ();
//                Qui viene controllato se l'authentication è gia popolata e se l'username al suo interno non è vuoto
                if (authentication == null && username != null) {
//                    nel caso sia vuoto viene popolato con questo metodo
                    addUserToSecurityContextAuthentication (request, username);
                } //avvia il filtro dopo aver inserito l'utente nel context
                filterChain.doFilter (request, response);
            } else {
                //Se il controllo di prima non viene superato viene pulito il context
                SecurityContextHolder.clearContext ();
            }
        } catch (Exception e) {
            handlerExceptionResolver.resolveException (request, response, null, e);
            System.out.println ("errore nel chatch filterchain " + e);
        }


    }

    //    Metodo per inserire l'utente nel context di security
    private void addUserToSecurityContextAuthentication (HttpServletRequest request,
                                                         String username) {
//    Qui assegnamo a un istanza di UserDetails (interfaccia implementata dal mio Users) un utente, preso tramite
//    l'username(in questo caso l'email) e il metodo che lo carica in questo caso fa anche una "preparazione" per il
//    context
        UserDetails userDetails = userService.loadUserByUsername (username);

//    Qui viene preparato un token con username e password a cui vengono passati in ordine userDetails(che contiene
//    tutti i dettagli dell user che abbiamo caricato prima), credential a null perchè se siamo arrivati qui vuol
//    dire che le credenziali sono gia state inserite, e poi i ruoli (authorities) che recuperiamo tramite
//    userDetails.getAuthorities()
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken (userDetails,
                null,
                userDetails.getAuthorities ());
//        Aggiunge al token i dettagli della richiesta http (ovvero da dove è stata fatta la chiamata) al token
//        speciale e in seguito viene aggiunta al securityContext
        token.setDetails (new WebAuthenticationDetailsSource ().buildDetails (request));
        SecurityContextHolder.getContext ().setAuthentication (token);


    }

}
