package it.siinfo.springboot2.service;

import it.siinfo.springboot2.entity.Users;
import it.siinfo.springboot2.repository.PaymentMethodRepository;
import it.siinfo.springboot2.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentMethodService {

    private PaymentMethodRepository paymentMethodRepository;
    private UserRepository userRepository;
    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository,
                                UserRepository userRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.userRepository = userRepository;
    }

public void createPaymentMethod(Long id){
        Optional<Users> optionalUsers= userRepository.findById(id);
        if (optionalUsers.isEmpty()){
            throw new EntityNotFoundException("User con id: "+ id + " non trovato");
        }
    


    }









}
