package it.siinfo.springboot2.controller;

import it.siinfo.springboot2.controller.interfaces.CategoryController;
import it.siinfo.springboot2.dto.CategoryDTO;
import it.siinfo.springboot2.dto.ProductDTO;
import it.siinfo.springboot2.service.CategoryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class CategoruyControllerImpl implements CategoryController {

    private CategoryService service;

    public CategoruyControllerImpl (CategoryService service) {
        this.service = service;
    }

    @Override
    public void create (@RequestBody CategoryDTO categoryDTO) {
        service.create (categoryDTO);
    }

    @Override
    public void addCategoryToProduct (@PathVariable Long proId,
                                      @PathVariable Long catId) {
        service.addCategoryToProduct (proId, catId);
    }

    @Override
    public List<CategoryDTO> getAllCategory () {
        return service.getAllCategory ();
    }

    @Override
    public void updateCategory (Long id,
                                CategoryDTO categoryDTO) {
    service.updateCategory (id,categoryDTO);
    }

    @Override
    public void deleteCategoryToProduct (Long catId,
                                         Long prodId) {
    service.deleteCategoryToProduct (prodId,catId);
    }

    @Override
    public List<CategoryDTO> findCategoryByProdId (Long id) {
        return service.trovaUnNomePerStoMetodo (id);
    }

    @Override
    public List<ProductDTO> findProdByCat (Long id) {
        return service.trovaUnNomeAncheAQuesto (id);
    }

    @Override
    public List<ProductDTO> getAllWhereCatIsNotPresent () {
        return service.getAllWhereCategoryIsNotPresent ();
    }
}
