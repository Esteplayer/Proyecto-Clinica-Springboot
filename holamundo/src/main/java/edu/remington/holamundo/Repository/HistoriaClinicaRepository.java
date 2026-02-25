package edu.remington.holamundo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import edu.remington.holamundo.model.HistoriaClinica;

public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Long> {
    
    Page<HistoriaClinica> findByAnimalid(Long animalId, Pageable pageable);




        
}
