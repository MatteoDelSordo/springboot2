package it.siinfo.springboot2.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private Date expirationDate;

    @Column(nullable = false)
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private Users user;

    public PaymentMethod() {
    }


    public PaymentMethod(Long id, String cardNumber, Date expirationDate, String cvv, Users user) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
