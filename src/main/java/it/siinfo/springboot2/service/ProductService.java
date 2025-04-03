package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.ProductDTO;
import it.siinfo.springboot2.eccezioni.ResourceNotFoundException;
import it.siinfo.springboot2.entity.Product;
import it.siinfo.springboot2.entity.Supplier;
import it.siinfo.springboot2.entity.SupplierProduct;
import it.siinfo.springboot2.mapper.ProductMapper;
import it.siinfo.springboot2.mapper.SupplierMapper;
import it.siinfo.springboot2.repository.ProductRepository;
import it.siinfo.springboot2.repository.SupplierProductRepository;
import it.siinfo.springboot2.repository.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final SupplierProductRepository supplierProductRepository;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          ProductMapper productMapper,
                          SupplierRepository supplierRepository,
                          SupplierMapper supplierMapper,
                          SupplierProductRepository supplierProductRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
        this.supplierProductRepository = supplierProductRepository;
    }

    public ProductDTO createProduct(ProductDTO productDTO) {

        Product product = productMapper.toProduct(productDTO);
        productRepository.save(product);

        return productMapper.toProductDto(product);
    }

    public List<ProductDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();


        return productMapper.toProductDtoList(products);
    }

    public void updateProduct(Long id,
                              ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException (
                "Prodotto non trovata"));

        ProductDTO productToChange = productMapper.toProductDto(product);

        productToChange.setNome(productDTO.getNome());

        productRepository.save(productMapper.toProduct(productToChange));


    }

    public void deleteProduct(Long id) {

        productRepository.deleteById(id);

    }

    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Prodotto non trovato"));
        return productMapper.toProductDto(product);
    }

    public List<ProductDTO> getAllProductOfSupplier(Long id) {

//      Non so se sia giusto il fatto dell optional qui sotto, per il momento lo lascio come controllo,
//      ma dovrebbe funzionare tutto anche senza
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isPresent()) {
            List<SupplierProduct> supplierProduct = supplierProductRepository.findAllBySupplier_Id(id);
            List<Product> products = supplierProduct.stream().map(SupplierProduct::getProduct).toList();
            return productMapper.toProductDtoList(products);
        }

        return null;
    }


}
