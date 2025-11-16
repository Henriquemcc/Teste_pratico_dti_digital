package io.github.henriquemcc.dtidigital.testepratico.repository;

import io.github.henriquemcc.dtidigital.testepratico.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Long> {

}
