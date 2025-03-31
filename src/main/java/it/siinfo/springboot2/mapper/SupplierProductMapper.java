package it.siinfo.springboot2.mapper;

import it.siinfo.springboot2.dto.SupplierProductDTO;
import it.siinfo.springboot2.entity.SupplierProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierProductMapper {

    @Mapping(target = "supplierId", source = "supplier.id")
    @Mapping(target = "productId", source = "product.id")
    SupplierProductDTO toSupplierProductDto(SupplierProduct supplierProduct);

    @Mapping(target = "supplier.id", source = "supplierId")
    @Mapping(target = "product.id", source = "productId")
    SupplierProduct toSupplierProduct(SupplierProductDTO supplierProductDTO);

    List<SupplierProductDTO> toSupplierProductDtoList(List<SupplierProduct> supplierProductList);

    List<SupplierProduct> toSupplierProductList(List<SupplierProductDTO> supplierProductDTOList);
}
