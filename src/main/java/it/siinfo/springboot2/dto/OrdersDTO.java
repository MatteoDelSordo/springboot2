package it.siinfo.springboot2.dto;

import it.siinfo.springboot2.Enum.ProductType;

import java.sql.Timestamp;


public class OrdersDTO {
    private Long id;

    private String product;

    private Double amount;

    private Timestamp orderDate;

    private Long userId;

    private Long shipmentId;

    private ProductType productType;



    public OrdersDTO() {
    }

    public OrdersDTO(Timestamp orderDate,
                     Double amount,
                     String product, Long userId,
                     Long shipmentId, ProductType productType) {
        this.orderDate = orderDate;
        this.amount = amount;
        this.product = product;
        this.userId = userId;
        this.shipmentId = shipmentId;
        this.productType = productType;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
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

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }
}
