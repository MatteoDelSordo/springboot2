package it.siinfo.springboot2.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refreshTokenId;

    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    private Users user;

    private Instant scadenza;

    public RefreshToken () {
    }

    public RefreshToken (String token,
                         Users user,
                         Instant scadenza) {
        this.token = token;
        this.user = user;
        this.scadenza = scadenza;
    }

    public Long getRefreshTokenId () {
        return refreshTokenId;
    }

    public void setRefreshTokenId (Long id) {
        this.refreshTokenId = id;
    }

    public String getToken () {
        return token;
    }

    public void setToken (String token) {
        this.token = token;
    }

    public Users getUser () {
        return user;
    }

    public void setUser (Users user) {
        this.user = user;
    }

    public Instant getScadenza () {
        return scadenza;
    }

    public void setScadenza (Instant scadenza) {
        this.scadenza = scadenza;
    }
}
