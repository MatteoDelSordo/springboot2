package it.siinfo.springboot2.dto;

public class ErrorResponseDTO {

    private String errorCode;

    private String errorMessage;

    public ErrorResponseDTO () {
    }

    public ErrorResponseDTO (String errorCode,
                             String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode () {
        return errorCode;
    }

    public void setErrorCode (String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage () {
        return errorMessage;
    }

    public void setErrorMessage (String errorMessage) {
        this.errorMessage = errorMessage;
    }




}
