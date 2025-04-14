package it.siinfo.springboot2.service;

import it.siinfo.springboot2.dto.CategoryDTO;
import it.siinfo.springboot2.dto.ProductDTO;
import it.siinfo.springboot2.eccezioni.ResourceNotFoundException;
import it.siinfo.springboot2.entity.Category;
import it.siinfo.springboot2.entity.Product;
import it.siinfo.springboot2.mapper.CategoryMapper;
import it.siinfo.springboot2.mapper.ProductMapper;
import it.siinfo.springboot2.repository.CategoryRepository;
import it.siinfo.springboot2.repository.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {

    public CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public CategoryService (CategoryRepository categoryRepository,
                            CategoryMapper categoryMapper,
                            ProductRepository productRepository,
                            ProductMapper productMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public void create (CategoryDTO categoryDTO) {

        categoryRepository.save (categoryMapper.toCategory (categoryDTO));

    }


    public void addCategoryToProduct (java.lang.Long prodId,
                                      java.lang.Long catId) {
        Product product = productRepository.findById (prodId).orElseThrow (() -> new ResourceNotFoundException (
                "Prodotto non trovato"));

        Category category = categoryRepository.findById (catId).orElseThrow (() -> new ResourceNotFoundException (
                "Categoria non presente"));

        Set<Category> categorySet = new HashSet<> (Set.of ());

        categorySet.add (category);

        product.getCategories ().add (category);

        productRepository.save (product);

    }

    public void updateCategory (Long id,
                                @NotNull CategoryDTO categoryDTO) {

        Category category = categoryRepository.findById (id).orElseThrow (() -> new ResourceNotFoundException (
                "Categoria non esistente"));

        CategoryDTO categoryToSave = categoryMapper.toCategoryDto (category);
        categoryToSave.setName (categoryDTO.getName ());
        categoryRepository.save (categoryMapper.toCategory (categoryToSave));

    }

    public List<CategoryDTO> getAllCategory () {
        List<Category> list = categoryRepository.findAll ();
        List<CategoryDTO> dtoList = categoryMapper.toCategoryDtoList (list);
        return dtoList;

    }

    public void deleteCategory (java.lang.Long id) {

        categoryRepository.deleteById (id);

    }


    public void deleteCategoryToProduct (java.lang.Long prodId,
                                         java.lang.Long catId) {
        categoryRepository.deleteProductCategory (prodId, catId);

    }


    public List<CategoryDTO> trovaUnNomePerStoMetodo (java.lang.Long id) {

        return categoryMapper.toCategoryDtoList (categoryRepository.findByProducts_Id (id));

    }


    // trova i prodotti appartenenti a una categoria
    public List<ProductDTO> trovaUnNomeAncheAQuesto (java.lang.Long id) {

        return productMapper.toProductDtoList (productRepository.findByCategories_Id (id));

    }

    public List<ProductDTO> getAllWhereCategoryIsNotPresent () {

        return productMapper.toProductDtoList (productRepository.findByCategories_IdNull ());

//        List<ProductDTO> listDaCIclare = productMapper.toProductDtoList (productRepository.findAll ());
//
//
//        List<ProductDTO> listaDaRiempire = null;

//        for (ProductDTO productDTO : listDaCIclare) {
//            if (productDTO.getCategoriesDTO ().isEmpty ()) {
//
//                listaDaRiempire.add (productDTO);
//
//            }
//
//
//            return listaDaRiempire;
//
//        }

//        listDaCIclare.forEach (a -> {
//            if (a.getCategoriesDTO ().isEmpty ()) {
//                listaDaRiempire.add (a);
//            } return listaDaRiempire;
//        });


    }

    public List<CategoryDTO> findAllCategoryWhereProductIsNotPresent () {

        return categoryMapper.toCategoryDtoList (categoryRepository.findByProductsNull ());

    }


    public List<ProductDTO> productWithSameCategory (java.lang.Long productId) {

        Product product = productRepository.findById (productId).orElseThrow (() -> new ResourceNotFoundException (
                "Prodotto non trovato"));

        Set<Category> categorySetList = product.getCategories ();

        List<Product> productList = productRepository.findAll ();

        List<Product> prova =
                productList.stream ().filter (p -> p.getCategories ().equals (product.getCategories ())).toList ();

        return productMapper.toProductDtoList (prova);

    }


    public void addMultipleCategoriesToProduct (java.lang.Long productId,
                                                Set<java.lang.Long> category) {
        Product product = productRepository.findById (productId).orElseThrow (() -> new ResourceNotFoundException (
                "Prodotto non trovato"));

        Set<Category> categorieDaAggiungere = new HashSet<> (categoryRepository.findAllById (category));
        product.getCategories ().addAll (categorieDaAggiungere);

        productRepository.save (product);
    }


}
