package it.siinfo.springboot2.repository;

import it.siinfo.springboot2.dto.ProductDTO;
import it.siinfo.springboot2.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
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



}
