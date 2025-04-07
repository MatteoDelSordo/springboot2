package it.siinfo.springboot2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class ProductDTO {

    @NotBlank
    @NotNull
    private String nome;

    Set<CategoryDTO> categoriesDTO;

    public ProductDTO () {
    }

    public ProductDTO (String nome,
                       Set<CategoryDTO> categoriesDTO) {
        this.nome = nome;
        this.categoriesDTO = categoriesDTO;
    }

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public Set<CategoryDTO> getCategoriesDTO () {
        return categoriesDTO;
    }

    public void setCategoriesDTO (Set<CategoryDTO> categoriesDTO) {
        this.categoriesDTO = categoriesDTO;
    }
}

