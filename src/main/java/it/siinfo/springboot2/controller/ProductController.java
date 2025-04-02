package it.siinfo.springboot2.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Recupera tutti i prodotti", description = "Restituisce una lista di tutti i prodotti presenti nel sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista prodotti restituita con successo")
    })
    @GetMapping(path = "/getall")
    public List<ProductDTO> getAllProduct() {
        return productService.getAllProduct();
    }


    @Operation(summary = "Crea un nuovo prodotto", description = "Crea un nuovo prodotto nel sistema utilizzando i dati forniti nel DTO del prodotto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Prodotto creato con successo"),
            @ApiResponse(responseCode = "400", description = "Dati del prodotto non validi")
    })
    @PostMapping(path = "/create")
    public ProductDTO createproduct(@RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }



    @Operation(summary = "Aggiorna un prodotto", description = "Aggiorna i dettagli di un prodotto dato l'ID e il DTO contenente le informazioni aggiornate.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prodotto aggiornato con successo"),
            @ApiResponse(responseCode = "404", description = "Prodotto non trovato")
    })
    @PutMapping(path = "/update/{id}")
    public void upadateProduct(@PathVariable Long id,
                               @RequestBody ProductDTO productDTO) {
        productService.updateProduct(id, productDTO);
    }


    @Operation(summary = "Elimina un prodotto", description = "Elimina un prodotto dato l'ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prodotto eliminato con successo"),
            @ApiResponse(responseCode = "404", description = "Prodotto non trovato")
    })
    @DeleteMapping(path = "/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }



    @Operation(summary = "Recupera un prodotto per ID", description = "Restituisce i dettagli di un prodotto dato l'ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prodotto trovato"),
            @ApiResponse(responseCode = "404", description = "Prodotto non trovato")
    })
    @GetMapping(path = "/findbyid/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return productService.findById(id);
    }




    @Operation(summary = "Recupera tutti i prodotti di un fornitore", description = "Restituisce una lista di tutti i prodotti associati a un fornitore dato l'ID del fornitore.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista prodotti del fornitore restituita con successo"),
            @ApiResponse(responseCode = "404", description = "Fornitore non trovato")
    })
    @GetMapping(path = "/findproductbysupplier/{id}")
    public List<ProductDTO> getAllProductBySupId(@PathVariable Long id) {
        return productService.getAllProductOfSupplier(id);
    }
}
