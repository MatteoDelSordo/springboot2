package it.siinfo.springboot2.dto;

public class RefreshTokenRequestDTO {

    private String userName;
    private String token;

    public RefreshTokenRequestDTO () {
    }

    public RefreshTokenRequestDTO (String userName,
                                   String token) {
        this.userName = userName;
        this.token = token;
    }

    public String getUserName () {
        return userName;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }

    public String getToken () {
        return token;
    }

    public void setToken (String token) {
        this.token = token;
    }
}
