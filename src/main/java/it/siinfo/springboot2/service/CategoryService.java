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
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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


    public void addCategoryToProduct (Long prodId,
                                      Long catId) {
        Product product = productRepository.findById (prodId).orElseThrow (() -> new ResourceNotFoundException (
                "Prodotto non trovato"));
        Category category = categoryRepository.findById (catId).orElseThrow (() -> new ResourceNotFoundException (
                "Categoria non presente"));

//        product.getCategories ().add (category);
        ProductDTO productDTO = productMapper.toProductDto (product);
        CategoryDTO categoryDTO = categoryMapper.toCategoryDto (category);

        productDTO.getCategoriesDTO ().add (categoryDTO);
//        Product prodoctToSave = productMapper.toProduct (productDTO);
        productRepository.save (productMapper.toProduct (productDTO));

    }

    public List<CategoryDTO> getAllCategory () {
        List<Category> list = categoryRepository.findAll ();

        return categoryMapper.toCategoryDtoList (list);

    }

    public void updateCategory (Long id,
                                CategoryDTO categoryDTO) {

        Category category = categoryRepository.findById (id).orElseThrow (() -> new ResourceNotFoundException (
                "Categoria non esistente"));

        CategoryDTO categoryToSave = categoryMapper.toCategoryDto (category);
        categoryToSave.setName (categoryDTO.getName ());
        categoryRepository.save (categoryMapper.toCategory (categoryToSave));

    }

    public void deleteCategory (Long id) {

        categoryRepository.deleteById (id);

    }


    public void deleteCategoryToProduct (Long prodId,
                                         Long catId) {
        Product product = productRepository.findById (prodId).orElseThrow (() -> new ResourceNotFoundException (
                "Prodotto non trovato"));
        Category category = categoryRepository.findById (catId).orElseThrow (() -> new ResourceNotFoundException (
                "Categoria non presente"));

//        product.getCategories ().add (category);
        ProductDTO productDTO = productMapper.toProductDto (product);
        CategoryDTO categoryDTO = categoryMapper.toCategoryDto (category);

        productDTO.getCategoriesDTO ().remove (categoryDTO);
//        Product prodoctToSave = productMapper.toProduct (productDTO);
        productRepository.save (productMapper.toProduct (productDTO));

    }

    //    trova le categorie associate a un id di un prodotto
    public List<CategoryDTO> trovaUnNomePerStoMetodo (Long id) {

        return categoryMapper.toCategoryDtoList (categoryRepository.findByProducts_Id (id));

    }


    // trova i prodotti appartenenti a una categoria
    public List<ProductDTO> trovaUnNomeAncheAQuesto (Long id) {

        return productMapper.toProductDtoList (productRepository.findByCategories_Id (id));

    }

    public List<ProductDTO> getAllWhereCategoryIsNotPresent () {

        return productRepository.findByCategories_IdNull ().stream ().map (productMapper::toProductDto).collect (
                Collectors.toList ());

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

//aiuto, questa non sono proprio stato in grado di farla.
    public List<ProductDTO> productWithSameCategory (Long prodId) {

//        ProductDTO productDTO =
//                productMapper.toProductDto (productRepository.findById (prodId).orElseThrow (() -> new
//                ResourceNotFoundException (
//                "Prodotto non esistente")));
//
//        Set<CategoryDTO> categoryDTOList = productDTO.getCategoriesDTO ();
//
//        List<ProductDTO> dtoList = productMapper.toProductDtoList (productRepository.findAll ());
//
//        Set<CategoryDTO> categoryDTOLista = new HashSet<> ();
//
//        for (ProductDTO dto :dtoList) {
//           CategoryDTO categoryDTO = (CategoryDTO) dto.getCategoriesDTO ();
//            categoryDTOLista.add ();
//
//        }

        return productRepository.findRelatedProducts (prodId);
    }


    public void addMultipleCategoriesToProduct(Long productId, Set<Long> category) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Prodotto non trovato"));

        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(category));
        product.getCategories().addAll(categories);

        productRepository.save(product);
    }




}
