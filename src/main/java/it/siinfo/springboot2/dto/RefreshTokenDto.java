package it.siinfo.springboot2.dto;

import java.time.Instant;

public class RefreshTokenDto {

    private Long refreshTokenId;

    private String token;

    private Long userId;

    private Instant scadenza;

    public RefreshTokenDto () {
    }

    public RefreshTokenDto (String token,
                            Long userId,
                            Instant scadenza) {
        this.token = token;
        this.userId = userId;
        this.scadenza = scadenza;
    }

    public Long getRefreshTokenId () {
        return refreshTokenId;
    }

    public void setRefreshTokenId (Long refreshTokenId) {
        this.refreshTokenId = refreshTokenId;
    }

    public String getToken () {
        return token;
    }

    public void setToken (String token) {
        this.token = token;
    }

    public Long getUserId () {
        return userId;
    }

    public void setUserId (Long userId) {
        this.userId = userId;
    }

    public Instant getScadenza () {
        return scadenza;
    }

    public void setScadenza (Instant scadenza) {
        this.scadenza = scadenza;
    }
}
