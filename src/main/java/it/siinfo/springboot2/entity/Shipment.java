package it.siinfo.springboot2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.siinfo.springboot2.Enum.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String rackingNumber;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(nullable = false)
    private LocalDateTime estimatedDeliveryDate;
    @OneToOne(fetch = FetchType.LAZY)
    private Orders orders;
    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Address address;

    public Shipment() {
    }

    public Shipment(String rackingNumber,
                    Status status,
                    LocalDateTime estimatedDeliveryDate,
                    Orders orders,
                    Address address) {
        this.rackingNumber = rackingNumber;
        this.status = status;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.orders = orders;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRackingNumber() {
        return rackingNumber;
    }

    public void setRackingNumber(String rackingNumber) {
        this.rackingNumber = rackingNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
