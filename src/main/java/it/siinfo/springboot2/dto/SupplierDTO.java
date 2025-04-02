package it.siinfo.springboot2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class SupplierDTO {
    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    @Email
    private String contactEmail;

    @NotBlank
    @NotNull
    @Pattern(regexp = "^[0-9]{2,4} ([ ]?[0-9]{9}$)", message = "Numero di telefono non valido")
    private String phoneNumber;

    // Costruttori, Getter e Setter
    public SupplierDTO () {
    }

    public SupplierDTO (String name,
                        String contactEmail,
                        String phoneNumber) {
        this.name = name;
        this.contactEmail = contactEmail;
        this.phoneNumber = phoneNumber;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getContactEmail () {
        return contactEmail;
    }

    public void setContactEmail (String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getPhoneNumber () {
        return phoneNumber;
    }

    public void setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
