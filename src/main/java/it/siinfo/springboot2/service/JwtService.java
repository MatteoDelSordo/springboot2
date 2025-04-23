package it.siinfo.springboot2.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expire}")
    private Long jwtExp;


    public String generateToken (UserDetails utente) {

//        Prendi i ruoli come stringhe e li salvi in una lista
        List<String> roles = utente.getAuthorities ().stream ().map (GrantedAuthority::getAuthority).toList ();

//        prendi l'username(he puo esere la qualuncque)
        String username = utente.getUsername ();

//        venogno messe in una mappa con chiave valore in cuila chiave è una stringa
//        e il valore un oggetto in questo caso le variabili locali sopra
        Map<String, Object> claims = Map.of ("username", username, "roles", roles);

//      sto robo crea il jwt, in cui mette la mappa di prima in cui claims sono i ruoli e l'username (il payload)
//      il subject è chi ha creato il coso
//      issued e expiration sono le rispettivew date di creazione e scadenza
//      mentre signWIth da la chiave crtittografata tramite il metodo che gli viene passato come argomento
//        compact compatta!
        return Jwts.builder ().claims (claims).subject (username).issuedAt (new Date ()).expiration (new Date (new Date ().getTime () + jwtExp)).signWith (
                getSecretKey ()).compact ();

    }

    //    Questo metodo serve per estrarre i byte della chiave personalizzata e la cripa
//    a una direzione (credo) in modo che non possa essere accessivile
    private SecretKey getSecretKey () {
        byte[] bytes = jwtSecret.getBytes (StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor (bytes);

    }

    //    vabbe
    public String extractUsername (String jwtToken) {

//      return extractClaims (jwtToken).get ("username").toString ();
        return extractClaims (jwtToken).getSubject ();

    }

    //    Xd
    public List<String> extractRoles (String token) {

        return List.of (extractClaims (token).get ("roles").toString ());

    }

    //    controlla se il jwt è un token valido se è cistryuto correttamente e poi controlla se è scaduto
    public boolean validateToken (String token) {

        Jws<Claims> claimsJws = Jwts.parser ().verifyWith (getSecretKey ()).build ().parseSignedClaims (token);

        Date expiration = claimsJws.getPayload ().getExpiration ();

        if (expiration.before (new Date ())) {
            return false;
        }
        return true;

    }


    //  qua si prepara un lettore per il token, utilizzando la chiave personalizzata criptata,
//  una volta costruito legge il token e se valido ritorna i contenuti del token
    private Claims extractClaims (String token) {


        return Jwts.parser ().verifyWith (getSecretKey ()).build ().parseSignedClaims (token).getPayload ();
    }


}
