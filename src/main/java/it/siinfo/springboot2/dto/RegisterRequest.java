package it.siinfo.springboot2.dto;

public class RegisterRequest {

    private String userName;
    private String passWord;


    public RegisterRequest () {
    }

    public RegisterRequest (String userName,
                            String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName () {
        return userName;
    }

    public void setUserName (String userName) {
        this.userName = userName;
    }

    public String getPassWord () {
        return passWord;
    }

    public void setPassWord (String passWord) {
        this.passWord = passWord;
    }
}
