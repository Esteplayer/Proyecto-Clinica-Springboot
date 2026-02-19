package edu.remington.holamundo.service;

import org.hibernate.query.Page;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import edu.remington.holamundo.Repository.AcudienteRepository;
import edu.remington.holamundo.dto.AcudienteResponse;
import edu.remington.holamundo.model.Acudiente;



@Service
@Transactional
public class AcudienteService {
    private final AcudienteRepository acudienteRepository;

    public AcudienteService(AcudienteRepository acudienteRepository) {
        this.acudienteRepository = acudienteRepository;
    }

    public AcudienteResponse crear(AcudienteRequest request) {
        Acudiente acudiente = new Acudiente();
        applyRequest(acudiente, request);
        Acudiente saved = acudienteRepository.save(acudiente);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public Page<AcudienteResponse> listar(Pageable pageable) {
        return acudienteRepository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public AcudienteResponse obtener(Long id) {
        Acudiente acudiente = findAcudiente(id);
        return toResponse(acudiente);
    }

    public void eliminar(Long id) {
        Acudiente acudiente = findAcudiente(id);
        acudienteRepository.delete(acudiente);
    }

    private Acudiente findAcudiente(Long id) {
        return acudienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Acudiente no encontrado"));
    }

    private void applyRequest(Acudiente acudiente, AcudienteRequest request) {
        acudiente.setNombre(request.getNombre());
        acudiente.setDocumento(request.getDocumento());
        acudiente.setTelefono(request.getTelefono());
        acudiente.setTelefono2(request.getTelefono2());
        acudiente.setEmail(request.getEmail());
        acudiente.setEmail2(request.getEmail2());
        acudiente.setDireccion(request.getDireccion());
    }

    private AcudienteResponse toResponse(Acudiente acudiente) {
        AcudienteResponse response = new AcudienteResponse();
        response.setId(acudiente.getId());
        response.setNombre(acudiente.getNombre());
        response.setDocumento(acudiente.getDocumento());
        response.setTelefono(acudiente.getTelefono());
        response.setTelefono2(acudiente.getTelefono2());
        response.setEmail(acudiente.getEmail());
        response.setEmail2(acudiente.getEmail2());
        response.setDireccion(acudiente.getDireccion());
        return response;
    }
}
