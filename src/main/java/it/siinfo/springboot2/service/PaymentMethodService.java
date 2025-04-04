package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.PaymentMethodDTO;
import it.siinfo.springboot2.eccezioni.ResourceNotFoundException;
import it.siinfo.springboot2.entity.PaymentMethod;
import it.siinfo.springboot2.entity.Users;
import it.siinfo.springboot2.mapper.PaymentMethodMapper;
import it.siinfo.springboot2.repository.PaymentMethodRepository;
import it.siinfo.springboot2.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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
        Optional<Users> optionalUsers = userRepository.findById (id);
        if (optionalUsers.isEmpty ()) {
            throw new ResourceNotFoundException ("User con id: " + id + " non trovato");
        }
        Users user = optionalUsers.get ();
        PaymentMethod paymentMethod = paymentMethodMapper.toPaymentMethod (paymentMethodDTO);
        paymentMethod.setUser (user);
        paymentMethodRepository.save (paymentMethod);

    }


    @Transactional
    public void deletePaymentMethodForUser (Long id) {
        paymentMethodRepository.delete (id);
    }

    @Transactional
    public List<PaymentMethodDTO> getPaymentMethodForUser (Long id) {

        List<PaymentMethod> list = paymentMethodRepository.findAllByUserId (id);

        return paymentMethodMapper.toPaymentMethodListDto (list);
    }

    @Transactional
    public void update (Long id,
                        PaymentMethodDTO paymentMethodDTO) {

        Optional<PaymentMethod> optionalPayment = paymentMethodRepository.findById (id);

        if (optionalPayment.isEmpty ()) {
            log.warn ("Qualcosa si è rotto");
            throw new ResourceNotFoundException ("Metodo di pagamento non trovato");
        }
        PaymentMethod paymentMethodToChange = optionalPayment.get ();
        paymentMethodToChange.setCardNumber (paymentMethodDTO.getCardNumber ());
        paymentMethodToChange.setExpirationDate (paymentMethodDTO.getExpirationDate ());
        paymentMethodToChange.setCvv (paymentMethodDTO.getCvv ());
        paymentMethodRepository.save (paymentMethodToChange);

    }

    @Transactional
    public List<PaymentMethod> getAllPayments () {
        return paymentMethodRepository.findAll ();
    }

}
