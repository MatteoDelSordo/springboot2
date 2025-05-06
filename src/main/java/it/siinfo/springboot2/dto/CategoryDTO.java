package it.siinfo.springboot2.dto;

import java.util.Set;

public class CategoryDTO {

    private Long id;

    private String name;

    private Set<Long> productsId;


    public CategoryDTO () {
    }

    public CategoryDTO (String name,
                        Set<Long> productsDTO) {
        this.name = name;
        this.productsId = productsDTO;
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

    public Set<Long> getProductsId () {
        return productsId;
    }

    public void setProductsId (Set<Long> productsId) {
        this.productsId = productsId;
    }
}
