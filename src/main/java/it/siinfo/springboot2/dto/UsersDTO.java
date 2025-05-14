package it.siinfo.springboot2.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Set;

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

    @CreationTimestamp
//    Il @NotNull non è necessario dato che il dato viene gestito in automatico grazie a @CreationTimestamp,
//    anzi non va proprio messo, se lo si mette va in errore perchè lo richiede obbligatoriamente e va inserito a mano.
    private Timestamp createdAt;

    @NotNull(message = "Il numero di telefono è obbligatorio")
    @Size(min = 10, max = 10, message = "Il numero di telefono puo contenere solo 10 cifre")
//    @Max(value = 11, message = "numero di telefono troppo lungo")
//    @Min(value = 9, message = "numero di telefono troppo corto")
    @Pattern(regexp = "\\d+", message = "Il campo puo contenere solo numeri")
    private String phoneNumber;


    private AddressDTO address;


    private Set<Long> idRoles;

    public UsersDTO () {

    }

    public UsersDTO (String password,
                     String eMail,
                     String name,
                     Timestamp createdAt,
                     String phoneNumber,
                     AddressDTO address,
                     Set<Long> idRoles) {
        this.password = password;
        this.eMail = eMail;
        this.name = name;
        this.createdAt = createdAt;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.idRoles = idRoles;
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

    public String getPhoneNumber () {
        return phoneNumber;
    }

    public void setPhoneNumber (String phoneNumber) {
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

    public Set<Long> getIdRoles () {
        return idRoles;
    }

    public void setIdRoles (Set<Long> idRoles) {
        this.idRoles = idRoles;
    }
}
