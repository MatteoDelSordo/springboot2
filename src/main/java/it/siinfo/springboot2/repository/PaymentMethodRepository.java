package it.siinfo.springboot2.repository;

import it.siinfo.springboot2.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod,Long > {
}
