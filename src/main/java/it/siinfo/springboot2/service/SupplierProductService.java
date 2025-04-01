package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.ProductDTO;
import it.siinfo.springboot2.dto.SupplierDTO;
import it.siinfo.springboot2.dto.SupplierProductDTO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierProductService {

    private final SupplierProductRepository supplierProductRepository;
    private final SupplierProductMapper supplierProductMapper;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final ProductMapper productMapper;

    @Autowired
    public SupplierProductService(SupplierProductRepository supplierProductRepository,
                                  SupplierProductMapper supplierProductMapper,
                                  ProductRepository productRepository,
                                  SupplierRepository supplierRepository,
                                  SupplierMapper supplierMapper,
                                  ProductMapper productMapper) {
        this.supplierProductRepository = supplierProductRepository;
        this.supplierProductMapper = supplierProductMapper;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
        this.productMapper = productMapper;
    }

    public SupplierProduct createSupplierProduct(SupplierProductDTO supplierProductDTO) {

        SupplierProduct supplierProduct = supplierProductMapper.toSupplierProduct(supplierProductDTO);

        return supplierProductRepository.save(supplierProduct);
    }


    public List<SupplierProductDTO> getAllSupplierProducts() {
        List<SupplierProduct> supplierProductList = supplierProductRepository.findAll();
        return supplierProductMapper.toSupplierProductDtoList(supplierProductList);
    }


    public SupplierProductDTO getSupplierProductById(Long id) {
        Optional<SupplierProduct> supplierProduct = supplierProductRepository.findById(id);
        if (supplierProduct.isPresent()) {
            return supplierProductMapper.toSupplierProductDto(supplierProduct.get());
        } else {
            throw new RuntimeException("SupplierProduct con id: " + id + " non trovato");
        }
    }


    public void updateSupplierProduct(Long id,
                                      SupplierProductDTO supplierProductDTO) {
        Optional<SupplierProduct> optionalSupplierProduct = supplierProductRepository.findById(id);
        if (optionalSupplierProduct.isPresent()) {
            SupplierProduct supplierProduct = optionalSupplierProduct.get();

            supplierProduct.setPrice(supplierProductDTO.getPrice());
            supplierProduct.setQuantity(supplierProductDTO.getQuantity());

            SupplierProduct updatedSupplierProduct = supplierProductRepository.save(supplierProduct);

        } else {
            throw new RuntimeException("SupplierProduct not found with id: " + id);
        }
    }


    public void deleteSupplierProduct(Long id) {

        supplierProductRepository.deleteById(id);

    }

    public SupplierProductDTO findById(Long id) {
        return supplierProductRepository.findById(id).map(supplierProductMapper::toSupplierProductDto).orElseThrow(() -> new EntityNotFoundException(
                "Pippo"));
    }

    public SupplierProductDTO associateProductAtSupplier(Long idProd,
                                                         Long idSupp,
                                                         SupplierProductDTO dto) {
        Product product = productRepository.findById(idProd).orElseThrow(() -> new EntityNotFoundException(
                "Prodotto non trovato"));

        Supplier supplier = supplierRepository.findById(idSupp).orElseThrow(() -> new EntityNotFoundException(
                "Suppli non trovato"));

        ProductDTO productDTO = productMapper.toProductDto(product);

        SupplierDTO supplierDTO = supplierMapper.toSupplierDTO(supplier);


        dto.setSupplierId(idSupp);
        dto.setProductId(idProd);

        SupplierProduct supplierProduct = supplierProductRepository.save(supplierProductMapper.toSupplierProduct(dto));

        return supplierProductMapper.toSupplierProductDto(supplierProduct);

    }


    //    Se leggerai questo commento sappi che ho passato 30 minuti buoni a fare questo
//    metodo scervellandomi sulle possibili problematiche che poteva comportare
//    per poi rendermi conto che lo avevo gia fatto sopra in un minuto e mezzo.
//    Mi sento scemo
    public void modifyPriceAndQuantity(Long id,
                                       SupplierProductDTO supplierProductDTO) {
        Optional<SupplierProduct> optional = supplierProductRepository.findById(id);
        if (optional.isPresent()) {
            SupplierProduct product = optional.get();
            SupplierProductDTO dto = supplierProductMapper.toSupplierProductDto(product);
            dto.setPrice(supplierProductDTO.getPrice());
            dto.setQuantity(supplierProductDTO.getQuantity());

            supplierProductRepository.save(supplierProductMapper.toSupplierProduct(dto));

        }
        throw new EntityNotFoundException("Qualcosa è andato storto in supplierProductService");


    }
//Questo metodo lo lascio per ricordarmi di quando ho passato due ore a pensare a come slegare dalla
// tabella di collegamento prodotti e fornitori ed ho deciso di impostare a null le due fk invece di eliminare direttamente il record.
//    mi ricordo delle rimozioni logiche ma avevamo detto di non farle in questo progetto, anche se pesno che se finisco prima mi metto a farle

//    public boolean dissociateProductFromSupplier(Long id) {
//
//        Optional<SupplierProduct> optional = supplierProductRepository.findById(id);
//
//        SupplierProduct supplierProduct = optional.get();
//        SupplierProductDTO productDTO = supplierProductMapper.toSupplierProductDto(supplierProduct);
//
//        productDTO.set
//
//
//
//
//        return true;
//    }


}
