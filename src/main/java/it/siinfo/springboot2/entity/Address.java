package it.siinfo.springboot2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String zipCode;
    @Column(nullable = false)
    private String country;
    @OneToOne
    /*    Allora, so che è sbagliato, abbiamo passato circa due ore e un quarto con alex a cercare di capire cosa
    succedeva.
      Abbiamo provato di tutto, a swappare le joincolumn, abbiamo provato diversi approcci nei metodi, cambiato
      l'ordine in cui
      vengono salvate e settate le cose, ma non siamo riusciti a trovare soluzione.
      In ogni caso, dal lato opposto a dove c'era la join la l'id usato come fk non veniva salvato.
      Il metodo nel service a cui mi riferisco è createAddress */
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private Users user;
    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Shipment shipment;

    public Address() {
    }

    public Address(String street,
                   String city,
                   String state,
                   String zipCode,
                   String country,
                   Users user,
                   Shipment shipment) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.user = user;
        this.shipment = shipment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
}
