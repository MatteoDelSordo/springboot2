package it.siinfo.springboot2.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.siinfo.springboot2.dto.UsersDTO;
import it.siinfo.springboot2.entity.Users;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserController {


    @Operation(summary = "Recupera la lista degli utenti", description =
            "Restituisce una lista di tutti gli utenti " + "presenti nel database.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista utenti restituita con successo")})
    @GetMapping(path = "/lista")
    public List<UsersDTO> getUsers ();


    @Operation(summary = "Recupera la lista utenti ordinata per nome", description = "Restituisce una lista di " +
            "utenti" + " ordinati alfabeticamente per nome.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description =
            "Lista utenti ordinata restituita con " + "successo")})
    @GetMapping(path = "/listaordinata")
    public List<UsersDTO> getUsersOrderedByName ();

    @Operation(summary = "Recupera lista utenti con metodo JPA", description = "Restituisce una lista di utenti " +
            "utilizzando un metodo JPA personalizzato.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista utenti restituita con successo")})
    @GetMapping(path = "/listaordinataconjpa")
    public List<UsersDTO> metodoStrano ();

    @Operation(summary = "Recupera utenti per nome", description = "Restituisce una lista di utenti con il nome " +
            "specificato.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description =
            "Lista utenti con il nome specificato " + "restituita con successo"), @ApiResponse(responseCode = "404",
            description = "Nessun utente trovato con " + "questo nome")})
    @GetMapping(path = "/getbyname/{name}")
    public List<UsersDTO> getUsersByName (@PathVariable String name);

    @Operation(summary = "Recupera un utente per ID", description =
            "Restituisce un utente specifico in base all'ID " + "fornito.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Utente trovato"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")})
    @GetMapping(path = "/utente/{id}")
    public UsersDTO getUserById (@PathVariable Long id);


    @Operation(summary = "Crea un nuovo utente", description =
            "Aggiunge un nuovo utente al database utilizzando i " + "dati forniti nel body della richiesta.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Utente creato con successo"),
            @ApiResponse(responseCode = "400", description = "Dati non validi forniti nella richiesta")})
    @PostMapping(path = "/add")
    public UsersDTO createUser (@RequestBody UsersDTO usersDto);

    @Operation(summary = "Elimina un utente per ID", description =
            "Rimuove un utente dal database in base all'ID " + "fornito.")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Utente eliminato con successo"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")})
    @DeleteMapping(path = "/deleteid/{id}")
    public void deleteUserById (@PathVariable Long id);

    @Operation(summary = "Modifica il nome e l'email di un utente", description = "Aggiorna il nome e l'email " +
            "dell'utente con ID specificato.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Utente aggiornato con successo"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato"), @ApiResponse(responseCode = "400"
            , description = "Dati non validi forniti nella richiesta")})
    @PutMapping(path = "/modifica/{id}")
    public void updateNameAndEmailUserById (@PathVariable Long id,
                                            @RequestBody UsersDTO usersDto);

    @Operation(summary = "Reset della password utente", description = "Reimposta la password di un utente con ID " +
            "specificato.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Password resettata con successo"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")})
    @PutMapping(path = "/resetpw/{id}")
    public void resetPwById (@PathVariable Long id,
                             @RequestBody UsersDTO usersDto);
}
