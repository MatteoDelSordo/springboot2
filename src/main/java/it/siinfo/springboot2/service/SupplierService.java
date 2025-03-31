package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.SupplierDTO;
import it.siinfo.springboot2.entity.Supplier;
import it.siinfo.springboot2.mapper.SupplierMapper;
import it.siinfo.springboot2.repository.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierService(SupplierRepository supplierRepository,
                           SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }


    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {

        Supplier supplier = supplierMapper.toSupplier(supplierDTO);
        supplierRepository.save(supplier);

        return supplierMapper.toSupplierDTO(supplier);
    }

    public SupplierDTO findById(Long id) {

        return supplierRepository.findById(id).map(supplierMapper::toSupplierDTO)
                .orElseThrow(() -> new EntityNotFoundException("supplier non trovato"));
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


}
