package it.siinfo.springboot2.dto;

public class KpiDTO {

    private Long totalOrders;
    private Long totalSuppliers;
    private Long totalProducts;
    private Double averagePrice;
    private Long averageQuantityPerProduct;

    public KpiDTO() {
    }

    public KpiDTO(Long totalOrders,
                  Long totalSuppliers,
                  Long totalProducts,
                  Double averagePrice,
                  Long averageQuantityPerProduct) {
        this.totalOrders = totalOrders;
        this.totalSuppliers = totalSuppliers;
        this.totalProducts = totalProducts;
        this.averagePrice = averagePrice;
        this.averageQuantityPerProduct = averageQuantityPerProduct;
    }

    public Long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public Long getTotalSuppliers() {
        return totalSuppliers;
    }

    public void setTotalSuppliers(Long totalSuppliers) {
        this.totalSuppliers = totalSuppliers;
    }

    public Long getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(Long totalProducts) {
        this.totalProducts = totalProducts;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Long getAverageQuantityPerProduct() {
        return averageQuantityPerProduct;
    }

    public void setAverageQuantityPerProduct(Long averageQuantityPerProduct) {
        this.averageQuantityPerProduct = averageQuantityPerProduct;
    }
}
