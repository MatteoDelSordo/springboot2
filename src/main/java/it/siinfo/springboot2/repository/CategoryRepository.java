package it.siinfo.springboot2.repository;

import it.siinfo.springboot2.entity.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c inner join c.products products where products.id = ?1")
    List<Category> findByProducts_Id (Long id);

//    @Query("select c from Category c where c.products is null")
//    List<Category> findByProductsNull ();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM product_category WHERE product_id = :productId AND category_id = :categoryId",
            nativeQuery = true)
    void deleteProductCategory (@Param("productId") Long productId,
                                @Param("categoryId") Long categoryId);

    @Query(value = "SELECT c.* FROM category c LEFT JOIN product_category pc ON c.id = pc.category_id WHERE pc" +
            ".product_id IS NULL", nativeQuery = true)
    List<Category> findCatWithNoProd ();
    @Query(value = "select p.* from product p left join product_category pc on p.id = pc.product_id where pc.product_id and pc.category_id is null\n", nativeQuery = true)
    List<Category> findProdWithNoCat ();

}
