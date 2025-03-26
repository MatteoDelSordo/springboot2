package it.siinfo.springboot2.dto;

import it.siinfo.springboot2.Enum.Status;

import java.time.LocalDateTime;

public class ShipmentDTO {
    private String rackingNumber;
    private Status status;
    private LocalDateTime estimatedDeliveryDate;
    private OrdersDTO orders;
    private AddressDTO address;

    // Costruttori
    public ShipmentDTO() {
    }

    public ShipmentDTO(String rackingNumber,
                       Status status,
                       LocalDateTime estimatedDeliveryDate,
                       OrdersDTO orders,
                       AddressDTO address) {
        this.rackingNumber = rackingNumber;
        this.status = status;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.orders = orders;
        this.address = address;
    }

    // Getter e Setter
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

    public OrdersDTO getOrders() {
        return orders;
    }

    public void setOrders(OrdersDTO orders) {
        this.orders = orders;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }


}
