package it.siinfo.springboot2.controller;

import it.siinfo.springboot2.dto.SupplierProductDTO;
import it.siinfo.springboot2.service.SupplierProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/supprod")
public class SupplierProductController {

    private SupplierProductService SPService;

    @Autowired
    public SupplierProductController(SupplierProductService productService) {
        this.SPService = productService;
    }

    @PostMapping(path = "/create")
    public void createSupplierProduct(SupplierProductDTO supplierProductDTO) {

        SPService.createSupplierProduct(supplierProductDTO);
    }

    @GetMapping(path = "/getall")
    public List<SupplierProductDTO> getAll() {

        return SPService.getAllSupplierProducts();
    }

    @PutMapping(path = "/update/{id}")
    public void updateSupplierProduct(@PathVariable Long id,
                                      @RequestBody SupplierProductDTO dto) {

        SPService.updateSupplierProduct(id, dto);
    }

    @DeleteMapping(path = "/delete{id}")
    public void deleteById(@PathVariable Long id) {

        SPService.deleteSupplierProduct(id);
    }

    @GetMapping(path = "/findbyid/{id}")
    public SupplierProductDTO findById(@PathVariable Long id) {

        return SPService.findById(id);
    }

    @PostMapping(path = "/associate/{idPro}/{idSupp}")
    public SupplierProductDTO associate(@PathVariable Long idPro,
                                        @PathVariable Long idSupp,
                                        @RequestBody SupplierProductDTO dto) {

        return SPService.associateProductAtSupplier(idPro, idSupp, dto);
    }



}
