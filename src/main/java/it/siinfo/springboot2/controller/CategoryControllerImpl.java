package it.siinfo.springboot2.controller;

import it.siinfo.springboot2.controller.interfaces.CategoryController;
import it.siinfo.springboot2.dto.CategoryDTO;
import it.siinfo.springboot2.dto.ProductDTO;
import it.siinfo.springboot2.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/category")
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService service;

    public CategoryControllerImpl (CategoryService service) {
        this.service = service;
    }

    @PostMapping(path = "/create")
    @Override
    public void create (@RequestBody CategoryDTO categoryDTO) {
        service.create (categoryDTO);
    }

    @PutMapping(path = "/add_cat_to_prod/products/{proId}/categories/{catId}")
    @Override
    public void addCategoryToProduct (@PathVariable Long proId,
                                      @PathVariable Long catId) {
        service.addCategoryToProduct (proId, catId);
    }

    @GetMapping(path = "/get_all_cat")
    @Override
    public List<CategoryDTO> getAllCategory () {
        return service.getAllCategory ();
    }

    @PutMapping(path = "/update_cat/categories/{id}")
    @Override
    public void updateCategory (@PathVariable Long id,
                                CategoryDTO categoryDTO) {
        service.updateCategory (id, categoryDTO);
    }

    @DeleteMapping(path = "/delete_cat_to_prod/products/{prodId}/categories/{catId}")
    @Override
    public void deleteCategoryToProduct (Long catId,
                                         Long prodId) {
        service.deleteCategoryToProduct (prodId, catId);
    }

    @GetMapping(path = "find_cat_by_prod_id/products/{id}")
    @Override
    public List<CategoryDTO> findCategoryByProdId (java.lang.Long id) {
        return service.findCategoryByProdId (id);
    }

    @GetMapping(path = "find_pro_by_cat_id/categories/{id}")
    @Override
    public List<ProductDTO> findProdByCat (java.lang.Long id) {
        return service.trovaUnNomeAncheAQuesto (id);
    }

    @GetMapping(path = "/uncategorized")
    @Override
    public List<ProductDTO> getAllWhereCatIsNotPresent () {
        return service.getAllWhereCategoryIsNotPresent ();
    }

    @GetMapping(path = "/empty")
    @Override
    public List<CategoryDTO> findAllCategoryWhereProductIsNotPresent () {
        return service.findAllCategoryWhereProductIsNotPresent ();
    }

    @GetMapping(path = "/products/{prodId}/related")
    @Override
    public List<ProductDTO> productWithSameCategory (@PathVariable Long prodId) {
        return service.productWithSameCategory (prodId);
    }

    @GetMapping(path = "/add_in_bulk/{prodId}")
    @Override
    public void addMultipleCategoriesToProduct (@PathVariable Long prodId,
                                                @RequestBody Set<Long> category) {
        service.addMultipleCategoriesToProduct (prodId, category);
    }

    @DeleteMapping(path = "/delete_by_id/{id}")
    @Override
    public void deleteById (@PathVariable java.lang.Long id) {
        service.deleteCategory (id);

    }


}
