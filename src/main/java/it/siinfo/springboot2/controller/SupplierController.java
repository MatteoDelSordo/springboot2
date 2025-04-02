package it.siinfo.springboot2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.siinfo.springboot2.dto.ProductDTO;
import it.siinfo.springboot2.dto.SupplierDTO;
import it.siinfo.springboot2.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/supplier")
public class SupplierController {

    private SupplierService supplierService;


    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;

    }
    @Operation(summary = "Crea un nuovo fornitore", description = "Crea un nuovo fornitore nel sistema e restituisce i dettagli del fornitore creato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fornitore creato con successo"),
            @ApiResponse(responseCode = "400", description = "Dati del fornitore non validi")
    })
    @PostMapping(path = "/create")
    public SupplierDTO createSupplier(@RequestBody SupplierDTO supplierDTO) {
        return supplierService.createSupplier(supplierDTO);
    }

    @Operation(summary = "Recupera tutti i fornitori", description = "Restituisce una lista di tutti i fornitori presenti nel database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista fornitori restituita con successo")
    })
    @GetMapping(path = "/getall")
    public List<SupplierDTO> getAllSupplier() {
        return supplierService.getAll();
    }

    @Operation(summary = "Elimina un fornitore", description = "Elimina un fornitore dato l'ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fornitore eliminato con successo"),
            @ApiResponse(responseCode = "404", description = "Fornitore non trovato")
    })
    @DeleteMapping(path = "/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
    }

    @Operation(summary = "Aggiorna i dettagli di un fornitore", description = "Aggiorna i dettagli di un fornitore esistente dato l'ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fornitore aggiornato con successo"),
            @ApiResponse(responseCode = "404", description = "Fornitore non trovato")
    })
    @PutMapping(path = "/update/{id}")
    public void updateSupplier(@PathVariable Long id,
                               @RequestBody SupplierDTO supplierDTO) {
        supplierService.updateSupplier(id, supplierDTO);
    }


    @Operation(summary = "Recupera un fornitore per ID", description = "Restituisce i dettagli di un fornitore dato l'ID. Se il fornitore non viene trovato, viene generata un'eccezione.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fornitore trovato"),
            @ApiResponse(responseCode = "404", description = "Fornitore non trovato")
    })
    @GetMapping(path = "/findbyid/{id}")
    public SupplierDTO findById(@PathVariable Long id) {
        return supplierService.findById(id);
    }


}
