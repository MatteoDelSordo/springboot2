package it.siinfo.springboot2.dto;

import java.time.LocalDate;

public class PaymentMethodDTO {
    private String maskedCardNumber;
    private LocalDate expirationDate;
    private Long userId;

    public PaymentMethodDTO() {
    }

    public PaymentMethodDTO(String maskedCardNumber, LocalDate expirationDate, Long userId) {
        this.maskedCardNumber = maskedCardNumber;
        this.expirationDate = expirationDate;
        this.userId = userId;
    }

    public String getMaskedCardNumber() {
        return maskedCardNumber;
    }

    public void setMaskedCardNumber(String maskedCardNumber) {
        this.maskedCardNumber = maskedCardNumber;
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
