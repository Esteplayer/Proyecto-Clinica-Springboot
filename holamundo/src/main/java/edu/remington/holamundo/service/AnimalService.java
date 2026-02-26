package edu.remington.holamundo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.remington.holamundo.Repository.AcudienteRepository;
import edu.remington.holamundo.Repository.AnimalRepository;
import edu.remington.holamundo.dto.AnimalResponse;
import edu.remington.holamundo.dto.AnimalRequest;
import edu.remington.holamundo.exception.ResourceNotFoundException;
import edu.remington.holamundo.model.Acudiente;
import edu.remington.holamundo.model.Animal;

@Service
@Transactional
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AcudienteRepository acudienteRepository;

   

    public AnimalService(AnimalRepository animalRepository, AcudienteRepository acudienteRepository) {
        this.animalRepository = animalRepository;
        this.acudienteRepository = acudienteRepository;
    }


    public AnimalResponse crear(AnimalRequest request) {
        Acudiente acudiente = findAcudiente(request.getAcudienteId());
        Animal animal = new Animal();
        applyRequest(animal, request, acudiente);
        Animal saved = animalRepository.save(animal);
        return toResponse(saved);
    }


    @Transactional(readOnly = true)
    public Page<AnimalResponse> listar(Pageable pageable) {
        return animalRepository.findAll(pageable).map(this::toResponse);

    }

    @Transactional(readOnly = true)
    public AnimalResponse obtenerPorId(Long id) {
        Animal animal = findAnimal(id);
        return toResponse(animal);
    }

    private Animal findAnimal(long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal no encontrado con id: " + id));
    }
    private Acudiente findAcudiente(long id) {
        return acudienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal no encontrado con id: " + id));
    }

     private void applyRequest(Animal animal, AnimalRequest request, Acudiente acudiente) {
        animal.setNombre(request.getNombre());
        animal.setEspecie(request.getEspecie());
        animal.setRaza(request.getRaza());
        animal.setEdad(request.getEdad());
        animal.setPeso(request.getPeso());
        animal.setFechaNacimiento(request.getFechaNacimiento());
        animal.setSexo(request.getSexo());
        animal.setAcudiente(acudiente);

    }

    public AnimalResponse toResponse(Animal animal) {
        AnimalResponse response = new AnimalResponse();
        response.setAcudienteId(animal.getId());
        response.setNombre(animal.getNombre());
        response.setEspecie(animal.getEspecie());
        response.setRaza(animal.getRaza());
        response.setPeso(animal.getPeso());
        response.setFechaNacimiento(animal.getFechaNacimiento());
        response.setSexo(animal.getSexo());
        response.setAcudienteId(animal.getAcudiente().getId());
        return response;
    }
}
