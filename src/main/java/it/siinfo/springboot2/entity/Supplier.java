package it.siinfo.springboot2.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Email
    private String contactEmail;
    @Column
    private String phoneNumber;


    public Supplier() {
    }

    public Supplier(Long id,
                    String name,
                    String contactEmail,
                    String phoneNumber) {
        this.id = id;
        this.name = name;
        this.contactEmail = contactEmail;
        this.phoneNumber = phoneNumber;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
