package edu.remington.holamundo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalRequest {
    
    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private String peso;
    private java.time.LocalDate fechaNacimiento;
    private String sexo;
    private Long acudienteId;

}
