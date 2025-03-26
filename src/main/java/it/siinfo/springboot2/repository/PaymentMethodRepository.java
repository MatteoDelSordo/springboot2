package it.siinfo.springboot2.repository;

import it.siinfo.springboot2.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod,Long > {


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM PaymentMethod p WHERE p.user.id = :userId")
    void delete(@Param("userId") Long id);

    List<PaymentMethod> findAllByUserId(Long userId);
    void deleteFromPaymentMethodByUserId(Long userId);




}




