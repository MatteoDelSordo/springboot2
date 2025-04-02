package it.siinfo.springboot2.dto;

import jakarta.validation.constraints.Min;

public class SupplierProductDTO {

    private Long supplierId;
    private Long productId;
    private Double price;
    @Min (1)
    private Integer quantity;

    public SupplierProductDTO() {
    }

    public SupplierProductDTO(Long supplierId,
                              Long productId,
                              Double price,
                              Integer quantity) {
        this.supplierId = supplierId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}