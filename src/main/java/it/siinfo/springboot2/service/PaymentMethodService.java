package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.PaymentMethodDTO;
import it.siinfo.springboot2.eccezioni.ResourceNotFoundException;
import it.siinfo.springboot2.entity.PaymentMethod;
import it.siinfo.springboot2.entity.Users;
import it.siinfo.springboot2.mapper.PaymentMethodMapper;
import it.siinfo.springboot2.repository.PaymentMethodRepository;
import it.siinfo.springboot2.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class PaymentMethodService {

    private static final Logger log = LoggerFactory.getLogger (PaymentMethodService.class);
    private final UserRepository userRepository;
    private final PaymentMethodMapper paymentMethodMapper;
    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodService (PaymentMethodRepository paymentMethodRepository,
                                 UserRepository userRepository,
                                 PaymentMethodMapper paymentMethodMapper) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.userRepository = userRepository;
        this.paymentMethodMapper = paymentMethodMapper;
    }

    @Transactional
    public void createPaymentMethodForUser (Long id,
                                            PaymentMethodDTO paymentMethodDTO) {
        log.info ("Creazione metodo di pagamento per l'utente con id: {}", id);
        Optional<Users> optionalUsers = userRepository.findById (id);
        if (optionalUsers.isEmpty ()) {
            throw new ResourceNotFoundException ("User con id: " + id + " non trovato");
        }
        Users user = optionalUsers.get ();
        log.debug ("Utente trovato:  {}", user);
        PaymentMethod paymentMethod = paymentMethodMapper.toPaymentMethod (paymentMethodDTO);
        paymentMethod.setUser (user);
        log.debug ("Metodo di pagamento {} assegnato a utente,", paymentMethod);

        paymentMethodRepository.save (paymentMethod);
        log.info ("Metodo di pagamento salvato");
    }


    @Transactional
    public void deletePaymentMethodForUser (Long id) {
        log.info ("Eliminazione metodo di pagamento per l'user con id: {}", id);
        paymentMethodRepository.delete (id);
        log.info ("Eliminazione completata");
    }

    @Transactional
    public List<PaymentMethodDTO> getPaymentMethodForUser (Long id) {

        log.info ("Ricerca metodi di pagamento per l'user con id: {}", id);
        List<PaymentMethod> list = paymentMethodRepository.findAllByUserId (id);
        log.debug ("Metodi di pagamento trovati: {}", list);

        log.info ("Ricerca completata");
        return paymentMethodMapper.toPaymentMethodListDto (list);
    }

    @Transactional
    public void update (Long id,
                        PaymentMethodDTO paymentMethodDTO) {

        log.info ("Modifica del metodo di pagamento con id: {}", id);
        Optional<PaymentMethod> optionalPayment = paymentMethodRepository.findById (id);

        if (optionalPayment.isEmpty ()) {
            log.warn ("Qualcosa si è rotto");
            throw new ResourceNotFoundException ("Metodo di pagamento non trovato");
        }
        PaymentMethodDTO paymentMethodToChange = paymentMethodMapper.toPaymentMethodDto (optionalPayment.get ());
        log.debug ("Metodo di pagamento trovato, dati prima del cambiamento: {}", paymentMethodToChange);

        paymentMethodToChange.setCardNumber (paymentMethodDTO.getCardNumber ());
        paymentMethodToChange.setExpirationDate (paymentMethodDTO.getExpirationDate ());
        paymentMethodToChange.setCvv (paymentMethodDTO.getCvv ());
        PaymentMethod paymentMethodChanged = paymentMethodMapper.toPaymentMethod (paymentMethodToChange);
        log.debug ("Metodo di pagamento dopo la modifica: {}", paymentMethodChanged);
        log.info ("Metodo di pagamento modificato");
        paymentMethodRepository.save (paymentMethodChanged);

    }

    @Transactional
    public List<PaymentMethodDTO> getAllPayments () {

        log.info ("Ricerca di tutti i metodi di pagamento");

        List<PaymentMethod> pippo = paymentMethodRepository.findAll ();

        log.info ("Metodi di pagamento trovati");

        return paymentMethodMapper.toPaymentMethodListDto (pippo);

    }

}
