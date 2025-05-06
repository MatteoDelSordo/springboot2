package it.siinfo.springboot2.service.Interfaces;

import it.siinfo.springboot2.dto.CategoryDTO;
import it.siinfo.springboot2.dto.ProductDTO;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    public void create (CategoryDTO categoryDTO);

    public void addCategoryToProduct (Long prodId,
                                      Long catId);

    public void updateCategory (Long id,
                                @NotNull CategoryDTO categoryDTO);

    public List<CategoryDTO> getAllCategory ();

    public void deleteCategory (Long id);

    public void deleteCategoryToProduct (Long prodId,
                                         Long catId);

    public List<CategoryDTO> findCategoryByProdId (Long id);

    public List<ProductDTO> trovaUnNomeAncheAQuesto (Long id);

    public List<ProductDTO> getAllWhereCategoryIsNotPresent ();

    public List<CategoryDTO> findAllCategoryWhereProductIsNotPresent ();

    public List<ProductDTO> productWithSameCategory (java.lang.Long productId);

    public void addMultipleCategoriesToProduct (Long productId,
                                                Set<Long> category);





}

