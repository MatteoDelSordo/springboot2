package it.siinfo.springboot2.dto;

import java.util.Objects;

public class LoginRequest {
    private String username;
    private String passWord;

    public LoginRequest () {
    }

    public LoginRequest (String username,
                         String passWord) {
        this.username = username;
        this.passWord = passWord;
    }

    public String getUsername () {
        return username;
    }

    public String getPassWord () {
        return passWord;
    }

    @Override
    public boolean equals (Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass () != this.getClass ()) {
            return false;
        }
        var that = (LoginRequest) obj;
        return Objects.equals (this.username, that.username) && Objects.equals (this.passWord, that.passWord);
    }

    @Override
    public int hashCode () {
        return Objects.hash (username, passWord);
    }

    @Override
    public String toString () {
        return "LoginRequest[" + "username=" + username + ", " + "passWord=" + passWord + ']';
    }

}
