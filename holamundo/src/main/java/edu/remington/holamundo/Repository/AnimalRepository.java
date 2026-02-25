package edu.remington.holamundo.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import edu.remington.holamundo.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    
    Page<Animal> findByNombreid(Long nombreid, Pageable pagea);
}
