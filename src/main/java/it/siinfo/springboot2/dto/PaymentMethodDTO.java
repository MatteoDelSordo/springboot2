package it.siinfo.springboot2.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.Date;

public class PaymentMethodDTO {
    @Valid
    @Pattern(regexp = "^[0-9]{16}$", message = "Il numero della carta deve contenere 16 cifre")
    private String cardNumber;
    @Future
    private Date expirationDate;
    @Pattern(regexp = "^[0-9]{3}$", message = "Cvv non valido")
    private String cvv;
    private Long userId;

    public PaymentMethodDTO () {
    }

    public PaymentMethodDTO (String cardNumber,
                             Date expirationDate,
                             String cvv,
                             Long userId) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.userId = userId;
    }

    public String getCardNumber () {
        return cardNumber;
    }

    public void setCardNumber (String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate () {
        return expirationDate;
    }

    public void setExpirationDate (Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv () {
        return cvv;
    }

    public void setCvv (String cvv) {
        this.cvv = cvv;
    }

    public Long getUserId () {
        return userId;
    }

    public void setUserId (Long userId) {
        this.userId = userId;
    }
}
