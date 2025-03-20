package it.siinfo.springboot2.dto;

import it.siinfo.springboot2.entity.Address;

import java.sql.Timestamp;

public class UsersDTO {
    private Long id;
    private String password;
    private String name;
    private String eMail;
    private Timestamp createdAt;
    private Integer phoneNumber;
    private Address address;

    public UsersDTO(Address address) {
        this.address = address;
    }

    public UsersDTO(Integer phoneNumber,
                    Timestamp createdAt,
                    String eMail,
                    String name,
                    String password,
                    Address address) {
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.eMail = eMail;
        this.name = name;
        this.password = password;
        this.address = address;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
