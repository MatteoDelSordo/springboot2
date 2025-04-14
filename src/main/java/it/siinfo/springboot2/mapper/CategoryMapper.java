package it.siinfo.springboot2.mapper;

import it.siinfo.springboot2.dto.Long;
import it.siinfo.springboot2.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "productsDTO", source = "products")
    Long toCategoryDto (Category category);

    @Mapping(target = "products", source = "productsDTO")
    Category toCategory (Long aLong);


    List<Category> toCategoryList (List<Long> aLong);

    Set<Category> toCategorySet (Set<Long> aLong);


    List<Long> toCategoryDtoList (List<Category> categorie);

    Set<Long> toCategoryDtoSet (Set<Category> categorie);


}
