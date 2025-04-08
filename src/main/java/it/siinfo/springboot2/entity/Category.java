package it.siinfo.springboot2.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @ManyToMany(mappedBy = "categories")
    Set<Product> products;

    public Category () {
    }

    public Category (String name,
                     Set<Product> products) {
        this.name = name;
        this.products = products;
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

    public Set<Product> getProducts () {
        return products;
    }

    public void setProducts (Set<Product> products) {
        this.products = products;
    }
}
