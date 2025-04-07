package it.siinfo.springboot2.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.siinfo.springboot2.dto.AddressDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AddressController {


    @Operation(summary = "Restituisce una lista", description = "Restituisce una lista di AddressDTO ")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Lista restituita con successo"),})
    @GetMapping(path = "/list")
    public List<AddressDTO> getAddressList ();

    @Operation(summary = "Restituisce un indirizzo dato il suo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indirizzo trovato"),
            @ApiResponse(responseCode = "404", description = "Indirizzo non trovato")
    })
    @GetMapping(path = "/addresbyid/{id}")
    public AddressDTO getAddressById (@PathVariable Long id);

    @Operation(summary = "Restituisce l'indirizzo associato a uno user tramite ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indirizzo utente restituito"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")
    })
    @GetMapping(path = "/addressbyuser/{userId}")
    public AddressDTO getAddressByUserId (@PathVariable Long userId);


    @Operation(summary = "Crea un indirizzo per un utente esistente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Indirizzo creato con successo"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")
    })
    @PostMapping(path = "/aggiungi/{userId}")
    public void createAddress (@PathVariable Long userId,
                               @Valid @RequestBody AddressDTO addressDTO);



    @Operation(summary = "Aggiorna un indirizzo esistente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Indirizzo aggiornato"),
            @ApiResponse(responseCode = "404", description = "Indirizzo non trovato")
    })
    @PutMapping(path = "/updateaddress/{id}")
    public void updateAddress (@PathVariable Long id,
                               @RequestBody AddressDTO addressDTO);




    @Operation(summary = "Elimina un indirizzo dato il suo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Indirizzo eliminato"),
            @ApiResponse(responseCode = "404", description = "Indirizzo non trovato")
    })
    @DeleteMapping(path = "/deleteaddress/{id}")
    public void deleteAddress (@PathVariable Long id);


}
