package it.siinfo.springboot2.mapper;

import it.siinfo.springboot2.dto.CategoryDTO;
import it.siinfo.springboot2.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "productsDTO", source = "products")
    CategoryDTO toCategoryDto (Category category);

    @Mapping(target = "products", source = "productsDTO")
    Category toCategory (CategoryDTO categoryDTO);

    List<Category> toCategoryList (List<CategoryDTO> categoryDTO);

    List<CategoryDTO> toCategoryDtoList (List<Category> categorie);


}
