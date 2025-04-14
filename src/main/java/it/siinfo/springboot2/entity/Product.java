package it.siinfo.springboot2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @ManyToMany
    @JsonManagedReference
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns =
    @JoinColumn(name = "category_id"))
    @Column
    Set<Category> categories;


    public Product () {
    }

    public Product (String nome) {
        this.nome = nome;


    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public Set<Category> getCategories () {
        return categories;
    }

    public void setCategories (Set<Category> categories) {
        this.categories = categories;
    }
}
