package it.siinfo.springboot2.dto;

import java.time.LocalDate;

public class PaymentMethodDTO {
    private String cardNumber;
    private LocalDate expirationDate;
    private Long userId;

    public PaymentMethodDTO() {
    }

    public PaymentMethodDTO(String cardNumber, LocalDate expirationDate, Long userId) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.userId = userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
