package it.siinfo.springboot2.mapper;

import it.siinfo.springboot2.dto.ProductDTO;
import it.siinfo.springboot2.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "nome", source = "nome")
    Product toProduct (ProductDTO productDTO);

    @Mapping(target = "nome", source = "nome")
    ProductDTO toProductDto (Product product);

    List<Product> toProductList (List<ProductDTO> productDTOs);

    List<ProductDTO> toProductDtoList (List<Product> products);

}
