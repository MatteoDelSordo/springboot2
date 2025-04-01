package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.SupplierDTO;
import it.siinfo.springboot2.entity.Product;
import it.siinfo.springboot2.entity.Supplier;
import it.siinfo.springboot2.entity.SupplierProduct;
import it.siinfo.springboot2.mapper.ProductMapper;
import it.siinfo.springboot2.mapper.SupplierMapper;
import it.siinfo.springboot2.mapper.SupplierProductMapper;
import it.siinfo.springboot2.repository.ProductRepository;
import it.siinfo.springboot2.repository.SupplierProductRepository;
import it.siinfo.springboot2.repository.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final SupplierProductRepository supplierProductRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SupplierProductMapper supplierProductMapper;

    public SupplierService(SupplierRepository supplierRepository,
                           SupplierMapper supplierMapper,
                           SupplierProductRepository supplierProductRepository,
                           ProductRepository productRepository,
                           ProductMapper productMapper,
                           SupplierProductMapper supplierProductMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
        this.supplierProductRepository = supplierProductRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.supplierProductMapper = supplierProductMapper;
    }


    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {

        Supplier supplier = supplierMapper.toSupplier(supplierDTO);
        supplierRepository.save(supplier);

        return supplierMapper.toSupplierDTO(supplier);
    }

    public SupplierDTO findById(Long id) {

        return supplierRepository.findById(id).map(supplierMapper::toSupplierDTO).orElseThrow(() -> new EntityNotFoundException(
                "supplier non trovato"));
    }

    public List<SupplierDTO> getAll() {

        List<Supplier> suppliers = supplierRepository.findAll();

        return suppliers.stream().map(supplierMapper::toSupplierDTO).collect(Collectors.toList());
    }

    public void updateSupplier(Long id,
                               SupplierDTO supplierDTO) {

        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Entità non trovata"));

        SupplierDTO supplierDaModificare = supplierMapper.toSupplierDTO(supplier);

        supplierDaModificare.setName(supplierDTO.getName());
        supplierDaModificare.setContactEmail(supplierDTO.getContactEmail());
        supplierDaModificare.setPhoneNumber(supplierDTO.getPhoneNumber());

        supplierRepository.save(supplierMapper.toSupplier(supplierDaModificare));

    }

    public void deleteSupplier(Long id) {
//        Supplier supplier = supplierRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Entità
//        non trovata"));
        supplierRepository.deleteById(id);
    }

    public List<SupplierDTO> getAllSupplierOfProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            List<SupplierProduct> supplierProductList = supplierProductRepository.findAllByProduct_Id(id);
            List<Supplier> suppliers = supplierProductList.stream().map(SupplierProduct::getSupplier).toList();
            return supplierMapper.toSupplierDTOList(suppliers);
        }
        return null;
    }


}
