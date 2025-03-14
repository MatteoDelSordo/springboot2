package it.siinfo.springboot2.dto;

import jakarta.persistence.Column;

import java.security.Timestamp;

public class OrdersDTO {
    private Long id;

    private String product;

    private Double amount;

    private Timestamp orderDate;

    private Long userId;

    public OrdersDTO() {
    }

    public OrdersDTO(Timestamp orderDate,
                     Double amount,
                     String product,Long userId) {
        this.orderDate = orderDate;
        this.amount = amount;
        this.product = product;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
