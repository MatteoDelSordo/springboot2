package it.siinfo.springboot2.controller.interfaces;

import it.siinfo.springboot2.dto.CategoryDTO;
import it.siinfo.springboot2.dto.ProductDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;


public interface CategoryController {


    public void create (@RequestBody CategoryDTO categoryDTO);


    public void addCategoryToProduct (@PathVariable Long proId,
                                      @PathVariable Long catId);

    public List<CategoryDTO> getAllCategory ();

    public void updateCategory (@PathVariable Long id,
                                @RequestBody CategoryDTO categoryDTO);

    public void deleteCategoryToProduct (@PathVariable Long catId,
                                         @PathVariable Long prodId);

    public List<CategoryDTO> findCategoryByProdId (@PathVariable Long id);

    public List<ProductDTO> findProdByCat (@PathVariable Long id);

    public List<ProductDTO> getAllWhereCatIsNotPresent ();

    public List<CategoryDTO> findAllCategoryWhereProductIsNotPresent();

    public List<ProductDTO> productWithSameCategory(Long prodId);

    public void addMultipleCategoriesToProduct(Long prodId, Set<Long> category);

    public void deleteById(Long id);
}
