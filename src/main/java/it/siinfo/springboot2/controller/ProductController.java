package it.siinfo.springboot2.controller;


import it.siinfo.springboot2.dto.ProductDTO;
import it.siinfo.springboot2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
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

}
