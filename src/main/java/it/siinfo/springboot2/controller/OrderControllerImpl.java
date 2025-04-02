package it.siinfo.springboot2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.siinfo.springboot2.controller.interfaces.OrderController;
import it.siinfo.springboot2.dto.OrdersDTO;
import it.siinfo.springboot2.dto.UserAndOrderDTO;
import it.siinfo.springboot2.entity.Orders;
import it.siinfo.springboot2.mapper.OrdersMapper;
import it.siinfo.springboot2.service.OrdersService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/orders")
public class OrderControllerImpl implements OrderController {


    private final OrdersService ordersService;
    private final OrdersMapper ordersMapper;


    public OrderControllerImpl(OrdersService ordersService,
                               OrdersMapper ordersMapper) {

        this.ordersService = ordersService;

        this.ordersMapper = ordersMapper;
    }


    @GetMapping(path = "/lista")
    public List<OrdersDTO> getOrders() {

        return ordersService.getOrders();

    }


    @GetMapping(path = "findbyid/{id}")
    public OrdersDTO getOrdersById(@PathVariable Long id,
                                   @RequestBody OrdersDTO ordersDTO) {

        return ordersService.findOrderById(id);

    }

    @Operation(summary = "Aggiungi un nuovo ordine a un utente", description = "Associa un nuovo ordine a un utente dato il suo ID e i dettagli dell'ordine.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordine aggiunto con successo all'utente"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")
    })
    @PostMapping(path = "/addorderanduser")
    public void createOrderAndUser(@RequestBody UserAndOrderDTO pippo) {
        System.out.println("Json rivevuto: " + pippo);
        System.out.println("Order: " + pippo.getOrdersDTO());
        System.out.println("User: " + pippo.getUsersDTO());
        ordersService.addOrderWithUser(pippo.getOrdersDTO(), pippo.getUsersDTO());

    }

    @Operation(summary = "Aggiungi un ordine a un utente", description = "Assegna un nuovo ordine a un utente esistente, utilizzando l'ID utente e i dettagli dell'ordine forniti.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordine aggiunto con successo all'utente"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")
    })
    @PostMapping(path = "/addordertouser/{idUser}")
    public void addOrderToUser(@RequestBody OrdersDTO ordersDTO,
                               @PathVariable Long idUser) {
        Orders order = ordersService.addOrderToUser(idUser, ordersDTO);
    }

    @Operation(summary = "Recupera tutti gli ordini di un utente tramite query", description = "Restituisce una lista di ordini di un utente dato l'ID, utilizzando una query specifica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista ordini restituita con successo"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")
    })
    @GetMapping(path = "/getquery/{id}")
    public List<OrdersDTO> getOrdersByUtenteIdQuery(@PathVariable Long id) {
        return ordersService.getAllByUserIdQuery(id);
    }

    @Operation(summary = "Recupera gli ordini di un utente", description = "Restituisce una lista di tutti gli ordini associati a un utente dato l'ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista ordini restituita con successo"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")
    })
    @GetMapping(path = "/getordersofuser/{usersId}")
    public List<OrdersDTO> getOrdersByUtenteId(@PathVariable Long usersId) {
        return ordersService.getOrdersOfUsersById(usersId);
    }

    @Operation(summary = "Aggiorna un ordine", description = "Aggiorna un ordine esistente con i nuovi dettagli forniti.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordine aggiornato con successo"),
            @ApiResponse(responseCode = "404", description = "Ordine non trovato")
    })
    @PutMapping(path = "/update/{id}")
    public void updateById(@RequestBody OrdersDTO ordersDTO,
                           @PathVariable Long id) {

        ordersService.addOrderToUser(id, ordersDTO);

    }


    @Operation(summary = "Elimina un ordine", description = "Elimina l'ordine dato il suo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordine eliminato con successo"),
            @ApiResponse(responseCode = "404", description = "Ordine non trovato")
    })
    @DeleteMapping(path = "/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        ordersService.deleteById(id);
    }

    @Operation(summary = "Recupera tutti gli ordini", description = "Restituisce una lista paginata di tutti gli ordini nel sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista ordini restituita con successo")
    })
    @GetMapping("/paginated")
    public Page<Orders> getAllOrders(@ParameterObject Pageable pageable) {
        return ordersService.getAllOrders(pageable);
    }

    @Operation(summary = "Aggiungi un ordine esistente a un utente", description = "Associa un ordine esistente a un utente dato l'ID dell'utente e l'ID dell'ordine.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordine esistente aggiunto all'utente"),
            @ApiResponse(responseCode = "404", description = "Ordine o utente non trovato")
    })
    @PutMapping(path = "/associateUtoO/{idUser}/{idOrder}")
    public OrdersDTO associateUserToOrder(@PathVariable Long idUser,
                                          @PathVariable Long idOrder) {

        return ordersMapper.toOrdersDTO(ordersService.addExistingOrderToUser(idUser, idOrder));
    }


}
