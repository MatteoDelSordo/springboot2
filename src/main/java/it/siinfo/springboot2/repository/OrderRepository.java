package it.siinfo.springboot2.repository;

import it.siinfo.springboot2.entity.Orders;
import it.siinfo.springboot2.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

   @Query(value = "SELECT o FROM Orders o WHERE o.product = :prodotto")
    List<Users> findByProduct(@Param("prodotto")  String prodotto);
    List<Users> findAllByOrderByAmountAsc();


//    stesso metodo ma con jpa

//    List<Orders> findOrdersByProduct(String prodotto);


}
