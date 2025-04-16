package it.siinfo.springboot2.mapper;

import it.siinfo.springboot2.dto.CategoryDTO;
import it.siinfo.springboot2.eccezioni.ResourceNotFoundException;
import it.siinfo.springboot2.entity.Category;
import it.siinfo.springboot2.entity.Product;
import it.siinfo.springboot2.repository.ProductRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "productsId", source = "products", qualifiedByName = "mapSetToLong")
    CategoryDTO toCategoryDto (Category category);

    @Mapping(target = "products", ignore = true)
    Category toCategory (CategoryDTO categoryDTO);


    @Named("mapSetToLong")
    default Set<Long> mapToEntity (Set<Product> products) {
        return products.stream ().map (Product::getId).collect (Collectors.toSet ());
    }


    List<Category> toCategoryList (List<CategoryDTO> categoryDTOList);

    Set<Category> toCategorySet (Set<CategoryDTO> categoryDTOSet);

    List<CategoryDTO> toCategoryDtoList (List<Category> categorie);

    Set<CategoryDTO> toCategoryDtoSet (Set<Category> categorie);


}
