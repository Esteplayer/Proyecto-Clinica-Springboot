package edu.remington.holamundo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import edu.remington.holamundo.Repository.AnimalRepository;
import edu.remington.holamundo.Repository.HistoriaClinicaRepository;
import edu.remington.holamundo.dto.HistoriaClinicaRequest;
import edu.remington.holamundo.dto.HistoriaClinicaResponse;
import edu.remington.holamundo.exception.ResourceNotFoundException;
import edu.remington.holamundo.model.Animal;
import edu.remington.holamundo.model.HistoriaClinica;

@Service
@Transactional
public class HistoriaClinicaService {

    
    private final HistoriaClinicaRepository historiaClinicaRepository;
    private final AnimalRepository animalRepository;


    public HistoriaClinicaService(HistoriaClinicaRepository historiaClinicaRepository,
            AnimalRepository animalRepository) {
        this.historiaClinicaRepository = historiaClinicaRepository;
        this.animalRepository = animalRepository;
    }

    public HistoriaClinicaResponse crear(HistoriaClinicaRequest request) {
        Animal animal = findAnimal(request.getAnimalId());
        HistoriaClinica historiaClinica = new HistoriaClinica();
        applyRequest(historiaClinica, request, animal);
        HistoriaClinica saved = historiaClinicaRepository.save(historiaClinica);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public Page<HistoriaClinicaResponse> listar(Pageable pageable) {
        return historiaClinicaRepository.findAll(pageable).map(this::toResponse);

    }

    @Transactional(readOnly = true)
    public HistoriaClinicaResponse obtenerPorId(Long id) {
        HistoriaClinica historiaClinica = findHistoriaClinica(id);
        return toResponse(historiaClinica);
    }

    
    private HistoriaClinica findHistoriaClinica(long id) {
        return historiaClinicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Acudiente no encontrado con id: " + id));
    }

    private Animal findAnimal(long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Acudiente no encontrado con id: " + id));
    }

    private void applyRequest(HistoriaClinica historiaClinica, HistoriaClinicaRequest request, Animal animal) {
        historiaClinica.setFechaConsulta(request.getFechaConsulta());
        historiaClinica.setMotivoConsulta(request.getMotivoConsulta());
        historiaClinica.setSintomas(request.getSintomas());
        historiaClinica.setDiagnostico(request.getDiagnostico());
        historiaClinica.setTratamiento(request.getTratamiento());
        historiaClinica.setMedicamientos(request.getMedicamentos());
        historiaClinica.setObservaciones(request.getObservaciones());
        historiaClinica.setVeterinario(request.getVeterinario());
        historiaClinica.setAnimal(animal);
    }

    public HistoriaClinicaResponse toResponse(HistoriaClinica historiaClinica) {
        HistoriaClinicaResponse response = new HistoriaClinicaResponse();
        response.setAnimalId(historiaClinica.getId());
        response.setFechaConsulta(historiaClinica.getFechaConsulta());
        response.setMotivoConsulta(historiaClinica.getMotivoConsulta());
        response.setSintomas(historiaClinica.getSintomas());
        response.setTratamiento(historiaClinica.getTratamiento());
        response.setMedicamentos(historiaClinica.getMedicamientos());
        response.setObservaciones(historiaClinica.getObservaciones());
        response.setVeterinario(historiaClinica.getVeterinario());
        response.setAnimalId(historiaClinica.getAnimal().getId());
        return response;
    }

}
