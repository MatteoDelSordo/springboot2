package it.siinfo.springboot2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductDTO {

    @NotBlank
    @NotNull
    private String nome;


    public ProductDTO() {
    }

    public ProductDTO(String nome) {
        this.nome = nome;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}

