package it.siinfo.springboot2.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.siinfo.springboot2.Enum.ProductType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Entity
@Table(schema = "esercizio2")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String product;
    @Column(nullable = false)
    Double amount;
    @CreationTimestamp
    Timestamp orderDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType productType;
    @OneToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    private Shipment shipment;

    public Orders() {
    }


    public Orders(Timestamp orderDate,
                  Double amount,
                  String product,
                  Users users,
                  ProductType productType,
                  Shipment shipment) {
        this.orderDate = orderDate;
        this.amount = amount;
        this.product = product;
        this.users = users;
        this.productType = productType;
        this.shipment = shipment;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
}
