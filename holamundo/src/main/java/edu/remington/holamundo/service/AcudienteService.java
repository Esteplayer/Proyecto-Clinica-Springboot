package edu.remington.holamundo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.remington.holamundo.Repository.AcudienteRepository;
import edu.remington.holamundo.dto.AcudienteResponse;
import edu.remington.holamundo.dto.AcudienteRequest;
import edu.remington.holamundo.exception.ResourceNotFoundException;
import edu.remington.holamundo.model.Acudiente;

@Service
@Transactional
public class AcudienteService {
    private final AcudienteRepository acudienteRepository;

    public AcudienteResponse crear(AcudienteRepository acudienteRepository) {
        this.acudienteRepository = acudienteRepository;
        applyRequest(acudiente, request);
        Acudiente saved = acudienteRepository.save(acudiente);
        return toResponse(saved);
    }

    private void applyRequest (Acudiente acudiente, AcudienteRequest request) {
        acudiente.setNombre(request.getNombre());
        acudiente.setDocumento(request.getDocumento());
        acudiente.setTelefono(request.getTelefono());
        acudiente.setEmail(request.getEmail());
        acudiente.setDireccion(request.getDireccion());
    }

    @Transactional(readOnly = true)
    public Page<AcudienteResponse> listar(Pageable pageable) {
        return acudienteRepository.findAll(pageable).map(this::toResponse);
            
    }

    @Transactional(readOnly = true)
    public AcudienteResponse obtenerPorId(Long id) {
        Acudiente acudiente = findAcudiente(id);
        return toResponse(acudiente);
    }

    private Acudiente findAcudiente(long id) {
        return acudienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Acudiente no encontrado con id: " + id));
    }

    public AcudienteResponse toResponse(Acudiente acudiente) {
        AcudienteResponse response = new AcudienteResponse();
        response.setNombre(acudiente.getNombre());
        response.setDocumento(acudiente.getDocumento());
        response.setTelefono(acudiente.getTelefono());
        response.setEmail(acudiente.getEmail());
        response.setDireccion(acudiente.getDireccion());
        return response;

    }
}