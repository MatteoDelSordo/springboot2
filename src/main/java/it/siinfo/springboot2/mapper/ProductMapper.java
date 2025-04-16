package it.siinfo.springboot2.mapper;

import it.siinfo.springboot2.dto.ProductDTO;
import it.siinfo.springboot2.entity.Category;
import it.siinfo.springboot2.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "categories", ignore = true)
    Product toProduct (ProductDTO productDTO);

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "categoriesId", source = "categories", qualifiedByName = "mapSetToLong")
    ProductDTO toProductDto (Product product);


    @Named("mapSetToLong")
    default Set<Long> map (Set<Category> category) {
        return category.stream ().map (Category::getId).collect (Collectors.toSet ());
    }



    List<Product> toProductList (List<ProductDTO> productDTOs);

    List<ProductDTO> toProductDtoList (List<Product> products);

}
