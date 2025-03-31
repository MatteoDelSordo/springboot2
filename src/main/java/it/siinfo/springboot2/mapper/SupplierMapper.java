package it.siinfo.springboot2.mapper;

import it.siinfo.springboot2.dto.SupplierDTO;
import it.siinfo.springboot2.entity.Supplier;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    Supplier toSupplier(SupplierDTO supplierDTO);

//    @Mapping(target = "supplierProducts", source = "supplierProduct")
    SupplierDTO toSupplierDTO(Supplier supplier);


    List<Supplier> toSupplierList(List<SupplierDTO> supplierDTOList);

    List<SupplierDTO> toSupplierDTOList(List<Supplier> supplierList);
}