package it.siinfo.springboot2.controller;

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


    @PostMapping (path = "/create/{id}")
    public void create(@PathVariable Long id,
                       @RequestBody PaymentMethodDTO paymentMethodDTO) {

        paymentMethodService.createPaymentMethodForUser(id, paymentMethodDTO);

    }


    @GetMapping(path = "/listbyuser/{id}")
    public List<PaymentMethodDTO> getByIdUtente(@PathVariable Long id) {

        return paymentMethodService.getPaymentMethodForUser(id);

    }

    @PutMapping(path = "/update/{id}")
    public void updateById(@PathVariable Long id,
                           @RequestBody PaymentMethodDTO paymentMethodDTO) {
        paymentMethodService.update(id, paymentMethodDTO);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteById(@PathVariable Long id) {

        paymentMethodService.deletePaymentMethodForUser(id);

    }

    @GetMapping(path = "/getall")
    public List<PaymentMethod> getallPaymentMethod() {
        return paymentMethodService.getAllPayments();
    }
}
