package de.danilova.mymarket.error_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e){
        MarketError err = new MarketError(HttpStatus.NOT_FOUND.value(), Collections.singleton(e.getMessage()));
        return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
    }
}
