package it.siinfo.springboot2.dto;

public class RefreshTokenResponseDTO {
    private String newToken;


    public RefreshTokenResponseDTO () {


    }

    public RefreshTokenResponseDTO (String nreToken) {
        this.newToken = nreToken;
    }

    public String getNreToken () {
        return newToken;
    }

    public void setNreToken (String nreToken) {
        this.newToken = nreToken;
    }
}




