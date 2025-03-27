package it.siinfo.springboot2.dto;

import it.siinfo.springboot2.Enum.Status;

import java.time.LocalDateTime;

public class ShipmentDTO {
    private String rackingNumber;
    private Status status;
    private LocalDateTime estimatedDeliveryDate;
    private Long ordersId;
    private Long addressId;

    // Costruttori
    public ShipmentDTO() {
    }

    public ShipmentDTO(String rackingNumber,
                       Status status,
                       LocalDateTime estimatedDeliveryDate,
                       Long ordersId,
                       Long addressId) {
        this.rackingNumber = rackingNumber;
        this.status = status;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.ordersId = ordersId;
        this.addressId = addressId;
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

    public Long getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Long ordersId) {
        this.ordersId = ordersId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }


}
