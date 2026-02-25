package edu.remington.holamundo.exception;

import java.time.LocalDateTime;

import lombok.*;
@Getter
@Setter
public class ErrorREsponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    
}
