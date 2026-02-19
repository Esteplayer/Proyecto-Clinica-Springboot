package edu.remington.holamundo.Repository;

import java.util.Optional;

import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import edu.remington.holamundo.model.HistoriaClinica;

public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Long> {
    
    Optional<HistoriaClinica> findByAnimalId(Long animalId, Pageable pageable);
}
