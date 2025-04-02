package it.siinfo.springboot2.dto;

import it.siinfo.springboot2.Enum.Status;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ShipmentDTO {
    @NotBlank
    @NotNull

    private String rackingNumber;

    @NotBlank
    @NotNull
    private Status status;

    @NotBlank
    @NotNull
    @Future
    private LocalDateTime estimatedDeliveryDate;

    @NotBlank
    @NotNull
    private Long ordersId;

    @NotBlank
    @NotNull
    private Long addressId;

    // Costruttori
    public ShipmentDTO () {
    }

    public ShipmentDTO (String rackingNumber,
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
    public String getRackingNumber () {
        return rackingNumber;
    }

    public void setRackingNumber (String rackingNumber) {
        this.rackingNumber = rackingNumber;
    }

    public Status getStatus () {
        return status;
    }

    public void setStatus (Status status) {
        this.status = status;
    }

    public LocalDateTime getEstimatedDeliveryDate () {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate (LocalDateTime estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public Long getOrdersId () {
        return ordersId;
    }

    public void setOrdersId (Long ordersId) {
        this.ordersId = ordersId;
    }

    public Long getAddressId () {
        return addressId;
    }

    public void setAddressId (Long addressId) {
        this.addressId = addressId;
    }


}
