package it.siinfo.springboot2.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.siinfo.springboot2.Enum.Role;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;


@Entity
@Table(schema = "esercizio2")
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String eMail;
    @Column(nullable = false)
    private String password;
    @CreationTimestamp
    private Timestamp createdAt;
    @Column
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JoinColumn(name = "address_id")
    private Address address;
    @Enumerated()
    private Role role;

    public Users () {
    }

    public Users (String name,
                  String eMail,
                  String password,
                  Timestamp createdAt,
                  String phoneNumber,
                  Address address,
                  Role role) {
        this.name = name;
        this.eMail = eMail;
        this.password = password;
        this.createdAt = createdAt;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        return List.of (new SimpleGrantedAuthority (role.name ()));
    }

    public String getPassword () {
        return password;
    }

    @Override
    public String getUsername () {
        return "";
    }

    public void setPassword (String password) {
        this.password = password;
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

    public Address getAddress () {
        return address;
    }

    public void setAddress (Address address) {
        this.address = address;
    }

    public Role getRole () {
        return role;
    }

    public void setRole (Role role) {
        this.role = role;
    }
}
