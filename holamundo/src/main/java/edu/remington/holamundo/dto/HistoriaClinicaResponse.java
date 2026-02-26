package edu.remington.holamundo.dto;

import lombok.*;

@Getter
@Setter
public class HistoriaClinicaResponse {

    private long id;
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