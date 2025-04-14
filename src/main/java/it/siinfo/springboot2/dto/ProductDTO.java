package it.siinfo.springboot2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class ProductDTO {

    @NotBlank
    @NotNull
    private String nome;

    Set<Long> categoriesId;

    public ProductDTO () {
    }

    public ProductDTO (String nome,
                       Set<Long> categoriesId) {
        this.nome = nome;
        this.categoriesId = categoriesId;
    }

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public Set<Long> getCategoriesId () {
        return categoriesId;
    }

    public void setCategoriesId (Set<Long> categoriesId) {
        this.categoriesId = categoriesId;
    }
}

