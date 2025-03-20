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
    List<Users> findByProduct(@Param("prodotto") String prodotto);

    List<Users> findAllByOrderByAmountAsc();

    List<Orders> findAllByUsers_Id(Long userId);
    @Query("SELECT o FROM Orders o WHERE o.users.id = :userId")
    List<Orders> findAllByUserIdQuerySchiantata(@Param("userId") Long userId);


//    stesso metodo ma con jpa

//    List<Orders> findOrdersByProduct(String prodotto);


}
