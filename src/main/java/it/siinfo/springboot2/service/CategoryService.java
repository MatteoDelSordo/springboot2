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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private static final Logger log = LoggerFactory.getLogger (CategoryService.class);
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

        log.info ("Creazione categoria");
        categoryRepository.save (categoryMapper.toCategory (categoryDTO));
        log.info ("Categoria creata");

    }


    public void addCategoryToProduct (Long prodId,
                                      Long catId) {

        log.info ("Aggiungo categoria con id: {} a prodotto con id; {}", catId, prodId);

        Product product = productRepository.findById (prodId).orElseThrow (() -> new ResourceNotFoundException (
                "Prodotto non trovato"));
        log.debug ("Trovato prodotto {}", product);

        Category category = categoryRepository.findById (catId).orElseThrow (() -> new ResourceNotFoundException (
                "Categoria non presente"));
        log.debug ("Trovata categoria {}", category);


        Set<Category> categorySet = new HashSet<> (Set.of ());

        categorySet.add (category);
        product.getCategories ().add (category);
        log.debug ("Cataegoria {} aggiunta a prodotto{}", category, product);

        productRepository.save (product);
        log.info ("Collegamento creato");
    }


    public void updateCategory (Long id,
                                @NotNull CategoryDTO categoryDTO) {

        log.info ("Richiesta di aggiornamento per la categoria con id: {}", id);

        Category category = categoryRepository.findById (id).orElseThrow (() -> new ResourceNotFoundException (
                "Categoria non esistente"));

        log.debug ("Categoria trovata: {}", category);

        CategoryDTO categoryToSave = categoryMapper.toCategoryDto (category);
        categoryToSave.setName (categoryDTO.getName ());

        log.debug ("Categoria aggiornata da salvare: {}", categoryToSave);

        categoryRepository.save (categoryMapper.toCategory (categoryToSave));

        log.info ("Categoria con id {} aggiornata con successo", id);
    }


    public List<CategoryDTO> getAllCategory () {
        log.info ("Esecuzione del metodo getAllCategory");

        List<Category> list = categoryRepository.findAll ();
        log.debug ("Categorie trovate nel repository: {}", list);

        List<CategoryDTO> dtoList = categoryMapper.toCategoryDtoList (list);
        log.debug ("Categorie convertite in DTO: {}", dtoList);

        return dtoList;
    }


    public void deleteCategory (Long id) {
        log.debug ("Recupero e eliminazione della categoria con id: {}", id);
        categoryRepository.deleteById (id);
        log.info ("Categoria con id: {} eliminata", id);
    }


    public void deleteCategoryToProduct (Long prodId,
                                         Long catId) {
        log.debug ("Eliminazione fisica dell record nella tabella di collegamento con product_id: {} e category_id{}",
                prodId,
                catId);
        categoryRepository.deleteProductCategory (prodId, catId);
        log.info ("Eliminazione associazione del prodotto con id: {} con la categoria con id: {}", prodId, catId);

    }


    public List<CategoryDTO> findCategoryByProdId (Long id) {

        log.info ("Ricerca categorie tramide id del prodotto");
        return categoryMapper.toCategoryDtoList (categoryRepository.findByProducts_Id (id));

    }


    public List<ProductDTO> trovaUnNomeAncheAQuesto (Long id) {
        log.info ("Ricerca prodotti tramite id categoria");
        return productMapper.toProductDtoList (productRepository.findByCategories_Id (id));

    }



    public List<ProductDTO> getAllWhereCategoryIsNotPresent () {

        return productMapper.toProductDtoList (productRepository.findByCategories_IdNull ());

    }


    public List<CategoryDTO> findAllCategoryWhereProductIsNotPresent () {

        return categoryMapper.toCategoryDtoList (categoryRepository.findCatWithNoProd ());

    }


    public List<ProductDTO> productWithSameCategory (java.lang.Long productId) {

        log.info ("Ricerca prodotti con una o più categorie in comune ");
        Product product = productRepository.findById (productId).orElseThrow (() -> new ResourceNotFoundException (
                "Prodotto non trovato"));

        Set<Category> categorySetList = product.getCategories ();

        List<Product> productList = productRepository.findAll ();

        List<Product> prova =
                productList.stream ().filter (p -> p.getCategories ().equals (product.getCategories ())).toList ();

        return productMapper.toProductDtoList (prova);

    }


    public void addMultipleCategoriesToProduct (Long productId,
                                                Set<Long> category) {
        Product product = productRepository.findById (productId).orElseThrow (() -> new ResourceNotFoundException (
                "Prodotto non trovato"));
        log.debug ("Prodotto trovato: {}", product);


        Set<Category> listaDiCategorie =
                category.stream ().map (id -> categoryRepository.findById (id).orElseThrow (() -> new ResourceNotFoundException (
                "Categoria inestitente"))).collect (Collectors.toSet ());
        log.debug ("Categorie recuperate dal DB: {}", listaDiCategorie);


        product.getCategories ().addAll (listaDiCategorie);

        productRepository.save (product);
        log.info ("Aggiunte {} categorie al prodotto con id {}", listaDiCategorie.size (), productId);

    }


}
