package edu.remington.holamundo.dto;

import lombok.*;

@Getter
@Setter
public class AnimalResponse {
   
    private long id;
    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private String peso;
    private java.time.LocalDate fechaNacimiento;
    private String sexo;
    
}
