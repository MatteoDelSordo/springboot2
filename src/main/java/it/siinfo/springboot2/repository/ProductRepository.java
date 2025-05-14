package it.siinfo.springboot2.repository;

import it.siinfo.springboot2.dto.ProductDTO;
import it.siinfo.springboot2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    Optional<Product> findBy_Categories (Object unknownAttr1);
//@Query("select p from Product p where p.categories.products.categories = ?1")
//List<Product> findByCategories_Products_Categories (Category categories);

    @Query("select p from Product p inner join p.categories categories where categories.id = ?1")
    List<Product> findByCategories_Id (Long id);

    @Query(value = "select p.* from product p left join product_category pc on p.id = pc.product_id where pc.product_id is null", nativeQuery = true)
    List<Product> findByCategories_IdNull ();


//    @Query("SELECT p FROM Product p WHERE p.id IN (" +
//            "SELECT pc.product.id FROM ProductCategory pc WHERE pc.category.id IN (" +
//            "SELECT pc2.category.id FROM ProductCategory pc2 WHERE pc2.product.id = :prodId" +
//            ") ) AND p.id != :prodId")
//    List<ProductDTO> findRelatedProducts(@Param("prodId") Long prodId);

    @Query("""
    SELECT DISTINCT p FROM Product p
    JOIN p.categories c
    WHERE c.id IN (
        SELECT c2.id FROM Product p2
        JOIN p2.categories c2
        WHERE p2.id = :productId
    )
""")
    List<ProductDTO> findProductsWithSameCategories(@Param("productId") Long productId);



}
