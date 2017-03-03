package fr.testjpa;

import org.springframework.data.jpa.repository.*;

public interface AnimalRepository extends JpaRepository<Animal, Long>{

}