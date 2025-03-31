package it.siinfo.springboot2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SupplierDTO {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String contactEmail;

    @NotBlank
    private String phoneNumber;

    // Costruttori, Getter e Setter
    public SupplierDTO() {
    }

    public SupplierDTO(String name,
                       String contactEmail,
                       String phoneNumber) {
        this.name = name;
        this.contactEmail = contactEmail;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
