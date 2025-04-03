package it.siinfo.springboot2.eccezioni;

import it.siinfo.springboot2.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleDbOperationException (Exception e) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO ("INTERNAL_SERVER_ERROR", e.getMessage ());
        return new ResponseEntity<> (errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    public ResponseEntity<ErrorResponseDTO> objectNotFoundException (ResourceNotFoundException exception) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO ("Non esiste un record con questo valore. ",
                exception.getMessage ());
        return new ResponseEntity<> (errorResponseDTO, HttpStatus.NOT_FOUND);
    }


}
