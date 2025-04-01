package it.siinfo.springboot2.controller;


import it.siinfo.springboot2.dto.ProductDTO;
import it.siinfo.springboot2.service.ProductService;
import it.siinfo.springboot2.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService productService;
    private final SupplierService supplierService;


    @Autowired
    public ProductController(ProductService productService,
                             SupplierService supplierService) {
        this.productService = productService;
        this.supplierService = supplierService;
    }

    @GetMapping(path = "/getall")
    public List<ProductDTO> getAllProduct() {
        return productService.getAllProduct();
    }

    @PostMapping(path = "/create")
    public ProductDTO createproduct(@RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @PutMapping(path = "/update/{id}")
    public void upadateProduct(@PathVariable Long id,
                               @RequestBody ProductDTO productDTO) {
        productService.updateProduct(id, productDTO);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping(path = "/findbyid/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping(path = "/findproductbysupplier/{id}")
    public List<ProductDTO> getAllProductBySupId(@PathVariable Long id) {
        return productService.getAllProductOfSupplier(id);
    }
}
