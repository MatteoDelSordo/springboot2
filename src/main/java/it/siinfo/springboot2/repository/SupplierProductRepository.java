package it.siinfo.springboot2.repository;

import it.siinfo.springboot2.entity.Product;
import it.siinfo.springboot2.entity.SupplierProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierProductRepository extends JpaRepository<SupplierProduct, Long> {

    public void findBysupplierId(Long id);
    List<SupplierProduct> findAllBySupplier_Id(Long id);
    List<SupplierProduct> findAllByProduct_Id(Long id);

}
