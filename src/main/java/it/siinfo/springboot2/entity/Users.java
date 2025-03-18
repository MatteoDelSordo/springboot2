package it.siinfo.springboot2.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;
import java.util.List;



@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String eMail;
    @Column(nullable = false)
    String password;
    @CreationTimestamp
    Timestamp createdAt;
    @Column
    Long phoneNumber;
    @OneToMany(mappedBy = "users",cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval = true )
    @JsonManagedReference
    private List<Orders> orders;


    public Users() {
    }

    public Users(Timestamp createdAt,
                 String password,
                 String eMail,
                 String name,
                 List<Orders> orders) {
        this.createdAt = createdAt;
        this.password = password;
        this.eMail = eMail;
        this.name = name;
        this.orders = orders;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
