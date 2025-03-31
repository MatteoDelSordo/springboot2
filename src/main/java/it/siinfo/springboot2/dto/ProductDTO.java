package it.siinfo.springboot2.dto;

public class ProductDTO {
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

