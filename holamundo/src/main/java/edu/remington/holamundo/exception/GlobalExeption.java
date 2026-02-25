package edu.remington.holamundo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExeption {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorREsponse> handleNotFound(ResourceNotFoundException ex,
        HttpServerResquest request){
            return buildError(ex,request, 404);
        }
    }
    
}
