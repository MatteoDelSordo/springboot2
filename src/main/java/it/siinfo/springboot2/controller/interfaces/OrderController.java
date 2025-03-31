package it.siinfo.springboot2.controller.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.siinfo.springboot2.dto.OrdersDTO;
import it.siinfo.springboot2.dto.UserAndOrderDTO;
import it.siinfo.springboot2.entity.Orders;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface OrderController {
    @Operation(summary = "Ritorna la lista completa", description = "Restituisce una lista di OrdersDTO ")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista di ordini restituita"),
            @ApiResponse(responseCode = "400", description = "Bad request"), @ApiResponse(responseCode = "500",
            description = "Errore del server")})
    @GetMapping(path = "/lista")
    public List<OrdersDTO> getOrders();

    @Operation(summary = "Ritorna un ordine specifico", description =
            "Restituisce un OrdersDTO (se esiste) tramite " + "un id, accetta un parametro Long id in entrata")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ordine trovato"),
            @ApiResponse(responseCode = "400", description = "Ordine non trovato"), @ApiResponse(responseCode = "500"
            , description = "Errore del server")})
    @GetMapping(path = "findbyid/{id}")
    public OrdersDTO getOrdersById(@PathVariable Long id,
                                   @RequestBody OrdersDTO ordersDTO);


// questo non serve piu
//    @PostMapping(path = "/addorder")
//    public void addOrder(@RequestBody OrdersDTO ordersDTO) {
//
//        Orders salvato = ordersService.addOrder(ordersDTO);
//    }

    @Operation(summary = "Crea un nuovo ordine e un nuovo utente", description = "Aggiunge un nuovo ordine e un " +
            "nuovo" + " utente al database.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Ordine e utente creati con successo"),
            @ApiResponse(responseCode = "400", description = "Dati non validi forniti nella richiesta")})
    @PostMapping(path = "/addorderanduser")
    public void createOrderAndUser(@RequestBody UserAndOrderDTO pippo);

    @Operation(summary = "Aggiunge un ordine a un utente esistente", description = "Crea un nuovo ordine e lo " +
            "associa" + " all'utente specificato tramite ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Ordine aggiunto con successo"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato"), @ApiResponse(responseCode = "400"
            , description = "Dati non validi")})
    @PostMapping(path = "/addordertouser/{idUser}")
    public void addOrderToUser(@RequestBody OrdersDTO ordersDTO,
                               @PathVariable Long idUser);

    @Operation(summary = "Recupera ordini di un utente (query)", description = "Restituisce una lista di ordini " +
            "associati all'utente specificato tramite ID, usando una query personalizzata.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista ordini restituita con successo"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")})
    @GetMapping(path = "/getquery/{id}")
    public List<OrdersDTO> getOrdersByUtenteIdQuery(@PathVariable Long id);

    @Operation(summary = "Recupera ordini di un utente", description =
            "Restituisce una lista di ordini associati " + "all'utente specificato tramite ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Lista ordini restituita con successo"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")})
    @GetMapping(path = "/getordersofuser/{usersId}")
    public List<OrdersDTO> getOrdersByUtenteId(@PathVariable Long usersId);


    @Operation(summary = "Aggiorna un ordine per ID", description = "Aggiorna le informazioni di un ordine esistente "
            + "specificato tramite ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ordine aggiornato con successo"),
            @ApiResponse(responseCode = "404", description = "Ordine non trovato"), @ApiResponse(responseCode = "400"
            , description = "Dati non validi")})
    @PutMapping(path = "/update/{id}")
    public void updateById(@RequestBody OrdersDTO ordersDTO,
                           @PathVariable Long id);

    @Operation(summary = "Elimina un ordine per ID", description =
            "Elimina un ordine esistente specificato tramite " + "ID.")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Ordine eliminato con successo"),
            @ApiResponse(responseCode = "404", description = "Ordine non trovato")})
    @DeleteMapping(path = "/delete/{id}")
    public void deleteById(@PathVariable Long id);

    @Operation(summary = "Recupera tutti gli ordini con paginazione", description = "Restituisce una pagina di " +
            "ordini" + ". Accetta parametri per paginazione e ordinamento.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            description = "Lista di ordini restituita con successo"), @ApiResponse(responseCode = "400", description
            = "Parametri di paginazione non validi")})
    @GetMapping("/paginated")
    public Page<Orders> getAllOrders(@ParameterObject Pageable pageable);

    @PutMapping(path = "/associateUtoO/{idUser}/{idOrder}")
    public OrdersDTO associateUserToOrder(@PathVariable Long idUser,
                                          @PathVariable Long idOrder);
}
