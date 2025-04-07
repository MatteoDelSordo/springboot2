package it.siinfo.springboot2.dto;

import it.siinfo.springboot2.entity.Product;

import java.util.Set;

public class CategoryDTO {

    private Long id;

    private String name;

    private Set<ProductDTO> productsDTO;


    public CategoryDTO () {
    }

    public CategoryDTO (String name,
                        Set<ProductDTO> productsDTO) {
        this.name = name;
        this.productsDTO = productsDTO;
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Set<ProductDTO> getProductsDTO () {
        return productsDTO;
    }

    public void setProductsDTO (Set<ProductDTO> productsDTO) {
        this.productsDTO = productsDTO;
    }
}
