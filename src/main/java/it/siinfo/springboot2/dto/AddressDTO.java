package it.siinfo.springboot2.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AddressDTO {

@Valid

    @NotNull(message = "Il campo street non può essere vuoto")
    @NotBlank(message = "Il campo street non può essere vuoto")
    private String street;

    @NotNull(message = "Il campo city non può essere vuoto")
    @NotBlank(message = "Il campo city non può essere vuoto")
    private String city;

    @NotNull(message = "Il campo state non può essere vuoto")
    @NotBlank(message = "Il campo state non può essere vuoto")
    private String state;

    @NotNull(message = "Il campo zipCode non può essere vuoto")
    @NotBlank(message = "Il campo zipCode non può essere vuoto")
    private String zipCode;

    @NotNull(message = "Il campo country non può essere vuoto")
    @NotBlank(message = "Il campo country non può essere vuoto")
    private String country;


    private Long userId;


    private Long shipmentId;


    public AddressDTO() {
    }

    public AddressDTO(String street,
                      String city,
                      String state,
                      String zipCode,
                      String country,
                      Long userId,
                      Long shipmentId) {

        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.userId = userId;
        this.shipmentId = shipmentId;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
