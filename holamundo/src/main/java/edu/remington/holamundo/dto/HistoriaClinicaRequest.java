package edu.remington.holamundo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoriaClinicaRequest {

    private String fechaConsulta;
    private String motivoConsulta;
    private String sintomas;
    private String diagnostico;
    private String tratamiento;
    private String medicamentos;
    private String observaciones;
    private String veterinario;
    private Long animalId;
}