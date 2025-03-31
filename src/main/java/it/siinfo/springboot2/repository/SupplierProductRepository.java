package it.siinfo.springboot2.repository;

import it.siinfo.springboot2.entity.SupplierProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierProductRepository extends JpaRepository<SupplierProduct, Long> {
}
