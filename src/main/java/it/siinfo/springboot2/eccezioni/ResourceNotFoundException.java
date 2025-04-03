package it.siinfo.springboot2.eccezioni;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException () {
    }

    public ResourceNotFoundException (String message) {
        super (message);
    }

public ResourceNotFoundException(String entita, String pippo){

}



}
