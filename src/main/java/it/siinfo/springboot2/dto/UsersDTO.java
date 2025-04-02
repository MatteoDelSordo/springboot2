package it.siinfo.springboot2.dto;

import it.siinfo.springboot2.entity.Address;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.sql.Timestamp;

public class UsersDTO {
    @Valid
    private Long id;

    @Pattern(regexp = "^[A-Za-z0-9!\"#$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]{8,16}$", message = "Password non valida: " +
            "deve" + " contenere da 8 a 16 caratteri, includere almeno una lettera, un numero e un carattere speciale.")
    private String password;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Email non valida")
    private String eMail;

    @Pattern(regexp = "^[A-Za-z]+$", message = "Il nome deve contenere solo lettere")
    private String name;

    @NotBlank(message = "Il campo di timesStamp è obbligatorio")
    @NotNull(message = "Il campo di timesStamp è obbligatorio")
    private Timestamp createdAt;

    @NotNull(message = "Il numero di telefono è obbligatorio")
    @NotBlank(message = "Il numero di telefono è obbligatorio")
    @Max(value = 9, message = "numero di telefono non valido")
    @Min(value = 9, message = "numero di telefono non valido")
    private Integer phoneNumber;


    private AddressDTO address;


    public UsersDTO () {

    }

    public UsersDTO (Integer phoneNumber,
                     Timestamp createdAt,
                     String eMail,
                     String name,
                     String password,
                     AddressDTO address) {
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.eMail = eMail;
        this.name = name;
        this.password = password;
        this.address = address;
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String geteMail () {
        return eMail;
    }

    public void seteMail (String eMail) {
        this.eMail = eMail;
    }

    public Timestamp getCreatedAt () {
        return createdAt;
    }

    public void setCreatedAt (Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getPhoneNumber () {
        return phoneNumber;
    }

    public void setPhoneNumber (Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public AddressDTO getAddress () {
        return address;
    }

    public void setAddress (AddressDTO address) {
        this.address = address;
    }
}
