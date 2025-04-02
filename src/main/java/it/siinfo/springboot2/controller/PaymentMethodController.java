package it.siinfo.springboot2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.siinfo.springboot2.dto.PaymentMethodDTO;
import it.siinfo.springboot2.entity.PaymentMethod;
import it.siinfo.springboot2.repository.PaymentMethodRepository;
import it.siinfo.springboot2.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/pagamento")
public class PaymentMethodController {

    private final PaymentMethodRepository paymentMethodRepository;
    PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodController(PaymentMethodService paymentMethodService,
                                   PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodService = paymentMethodService;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Operation(summary = "Crea un nuovo metodo di pagamento per un utente", description = "Associa un nuovo metodo di pagamento a un utente dato il suo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metodo di pagamento creato con successo"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")
    })
    public void create(@PathVariable Long id,
                       @RequestBody PaymentMethodDTO paymentMethodDTO) {

        paymentMethodService.createPaymentMethodForUser(id, paymentMethodDTO);

    }

    @Operation(summary = "Recupera i metodi di pagamento di un utente", description = "Restituisce una lista di tutti i metodi di pagamento associati a un utente dato l'ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metodi di pagamento restituiti con successo"),
            @ApiResponse(responseCode = "404", description = "Utente non trovato")
    })
    @GetMapping(path = "/listbyuser/{id}")
    public List<PaymentMethodDTO> getByIdUtente(@PathVariable Long id) {

        return paymentMethodService.getPaymentMethodForUser(id);

    }

    @Operation(summary = "Aggiorna un metodo di pagamento", description = "Aggiorna i dettagli di un metodo di pagamento dato l'ID e il DTO contenente le informazioni aggiornate.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metodo di pagamento aggiornato con successo"),
            @ApiResponse(responseCode = "404", description = "Metodo di pagamento non trovato")
    })
    @PutMapping(path = "/update/{id}")
    public void updateById(@PathVariable Long id,
                           @RequestBody PaymentMethodDTO paymentMethodDTO) {
        paymentMethodService.update(id, paymentMethodDTO);
    }

    @Operation(summary = "Elimina un metodo di pagamento per un utente", description = "Elimina il metodo di pagamento associato a un utente dato l'ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Metodo di pagamento eliminato con successo"),
            @ApiResponse(responseCode = "404", description = "Metodo di pagamento non trovato")
    })
    @DeleteMapping(path = "/delete/{id}")
    public void deleteById(@PathVariable Long id) {

        paymentMethodService.deletePaymentMethodForUser(id);

    }

    @Operation(summary = "Recupera tutti i metodi di pagamento", description = "Restituisce una lista di tutti i metodi di pagamento presenti nel sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista metodi di pagamento restituita con successo")
    })
    @GetMapping(path = "/getall")
    public List<PaymentMethod> getallPaymentMethod() {
        return paymentMethodService.getAllPayments();
    }
}
