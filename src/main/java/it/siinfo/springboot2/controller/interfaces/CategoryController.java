package it.siinfo.springboot2.controller.interfaces;

import it.siinfo.springboot2.dto.Long;
import it.siinfo.springboot2.dto.ProductDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;


public interface CategoryController {


    public void create (@RequestBody Long aLong);


    public void addCategoryToProduct (@PathVariable java.lang.Long proId,
                                      @PathVariable java.lang.Long catId);

    public List<Long> getAllCategory ();

    public void updateCategory (@PathVariable java.lang.Long id,
                                @RequestBody Long aLong);

    public void deleteCategoryToProduct (@PathVariable java.lang.Long catId,
                                         @PathVariable java.lang.Long prodId);

    public List<Long> findCategoryByProdId (@PathVariable java.lang.Long id);

    public List<ProductDTO> findProdByCat (@PathVariable java.lang.Long id);

    public List<ProductDTO> getAllWhereCatIsNotPresent ();

    public List<it.siinfo.springboot2.dto.Long> findAllCategoryWhereProductIsNotPresent();

    public List<ProductDTO> productWithSameCategory(java.lang.Long prodId);

    public void addMultipleCategoriesToProduct(java.lang.Long prodId, Set<java.lang.Long> category);

    public void deleteById(java.lang.Long id);
}
