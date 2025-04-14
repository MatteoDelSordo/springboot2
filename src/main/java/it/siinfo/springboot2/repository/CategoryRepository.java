package it.siinfo.springboot2.repository;

import it.siinfo.springboot2.dto.ProductDTO;
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

    @Query("select c from Category c where c.products is null")
    List<Category> findByProductsNull ();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM product_category WHERE product_id = :productId AND category_id = :categoryId",
            nativeQuery = true)
    void deleteProductCategory (@Param("productId") Long productId,
                                @Param("categoryId") Long categoryId);


}
