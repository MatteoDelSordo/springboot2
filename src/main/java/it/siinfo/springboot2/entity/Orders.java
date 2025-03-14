package it.siinfo.springboot2.entity;

import jakarta.persistence.*;

import java.security.Timestamp;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String product;
    @Column(nullable = false)
    Double amount;
    @Column(nullable = false,columnDefinition = "timestamp")
    Timestamp orderDate;
    @ManyToOne
    @JoinColumn(name="id_user")
    private Users users;

    public Orders() {
    }


    public Orders(Timestamp orderDate,
                  Double amount,
                  String product) {
        this.orderDate = orderDate;
        this.amount = amount;
        this.product = product;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
}
