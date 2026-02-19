package edu.remington.holamundo.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import edu.remington.holamundo.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    
    Optional<Animal> findByNombre(String nombre);
}
