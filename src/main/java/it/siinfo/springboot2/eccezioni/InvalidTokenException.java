package it.siinfo.springboot2.eccezioni;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException () {
    }

    public InvalidTokenException (String message) {
        super (message);
    }
}
