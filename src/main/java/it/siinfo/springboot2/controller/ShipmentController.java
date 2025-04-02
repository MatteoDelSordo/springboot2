package it.siinfo.springboot2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.siinfo.springboot2.dto.ShipmentDTO;
import it.siinfo.springboot2.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/spedizione")
public class ShipmentController {


    private final ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {

        this.shipmentService = shipmentService;

    }


    @Operation(summary = "Recupera tutte le spedizioni", description = "Restituisce una lista di tutte le spedizioni presenti nel sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista spedizioni restituita con successo")
    })
    @GetMapping(path = "/getall")
    public List<ShipmentDTO> getAllShipment() {

        return shipmentService.getAllShipments();

    }


    @Operation(summary = "Recupera una spedizione per ID", description = "Restituisce i dettagli di una spedizione dato l'ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spedizione trovata"),
            @ApiResponse(responseCode = "404", description = "Spedizione non trovata")
    })
    @GetMapping(path = "/getbyid/{id}")
    public ShipmentDTO getShipmentById(@PathVariable Long id) {

        return shipmentService.getShipmentById(id);

    }


    @Operation(summary = "Crea una nuova spedizione", description = "Crea una nuova spedizione associata a un ordine e a un indirizzo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spedizione creata con successo"),
            @ApiResponse(responseCode = "404", description = "Ordine o indirizzo non trovato")
    })
    @PostMapping(path = "/create/{idOrder}/{idAddress}")
    public void createShipment(@PathVariable Long idOrder,
                               @PathVariable Long idAddress,
                               @RequestBody ShipmentDTO shipmentDTO) {

        shipmentService.createShipment(idOrder, idAddress, shipmentDTO);

    }


    @Operation(summary = "Aggiorna i dettagli di una spedizione", description = "Aggiorna i dettagli di una spedizione esistente dato l'ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spedizione aggiornata con successo"),
            @ApiResponse(responseCode = "404", description = "Spedizione non trovata")
    })
    @PutMapping(path = "/update/{id}")
    public void updateShipment(@PathVariable Long id,
                               @RequestBody ShipmentDTO shipmentDTO) {
        shipmentService.updateShipment(id, shipmentDTO);
    }


    @Operation(summary = "Aggiorna lo stato di una spedizione", description = "Aggiorna lo stato di una spedizione esistente dato l'ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stato della spedizione aggiornato con successo"),
            @ApiResponse(responseCode = "404", description = "Spedizione non trovata")
    })
    @PutMapping(path = "/updatestatus/{id}")
    public void updateShipmentStatus(@PathVariable Long id,
                                     @RequestBody ShipmentDTO shipmentDTO) {
        shipmentService.updateShipmentStatus(id, shipmentDTO);
    }



    @Operation(summary = "Elimina una spedizione", description = "Elimina una spedizione dato l'ID. La spedizione viene dissociata anche dall'ordine e dall'indirizzo associato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spedizione eliminata con successo"),
            @ApiResponse(responseCode = "404", description = "Spedizione non trovata")
    })
    @DeleteMapping("/delete/{id}")
    public void deleteShipment(@PathVariable Long id) {
        shipmentService.deleteShipment(id);

    }


    @Operation(summary = "Recupera la spedizione di un ordine", description = "Restituisce i dettagli della spedizione associata a un ordine dato l'ID dell'ordine.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spedizione trovata per ordine"),
            @ApiResponse(responseCode = "404", description = "Ordine non trovato")
    })
    @GetMapping(path = "/getbyorderid/{id}")

    public ShipmentDTO getByOrderId(Long id) {

        return shipmentService.getShipmentByOrderId(id);
    }


}
