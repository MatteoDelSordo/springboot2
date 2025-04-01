package it.siinfo.springboot2.controller;

import it.siinfo.springboot2.dto.ProductDTO;
import it.siinfo.springboot2.dto.SupplierDTO;
import it.siinfo.springboot2.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/supplier")
public class SupplierController {

    private SupplierService supplierService;


    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;

    }

    @PostMapping(path = "/create")
    public SupplierDTO createSupplier(@RequestBody SupplierDTO supplierDTO) {
        return supplierService.createSupplier(supplierDTO);
    }

    @GetMapping(path = "/getall")
    public List<SupplierDTO> getAllSupplier() {
        return supplierService.getAll();
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
    }

    @PutMapping(path = "/update/{id}")
    public void updateSupplier(@PathVariable Long id,
                               @RequestBody SupplierDTO supplierDTO) {
        supplierService.updateSupplier(id, supplierDTO);
    }

    @GetMapping(path = "/findbyid/{id}")
    public SupplierDTO findById(@PathVariable Long id) {
        return supplierService.findById(id);
    }


}
