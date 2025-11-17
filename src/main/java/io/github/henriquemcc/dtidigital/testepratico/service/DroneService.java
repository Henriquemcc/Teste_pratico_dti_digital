package io.github.henriquemcc.dtidigital.testepratico.service;

import io.github.henriquemcc.dtidigital.testepratico.exception.NotFoundException;
import io.github.henriquemcc.dtidigital.testepratico.model.Drone;
import io.github.henriquemcc.dtidigital.testepratico.repository.DroneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneService {
    private final DroneRepository droneRepository;
    private final String notFoundMessage = "Drone não encontrado";

    public DroneService(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    public List<Drone> listar() {
        return droneRepository.findAll();
    }

    public Drone buscarPorId(long id) {
        return droneRepository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessage));
    }

    public Drone cadastrar(Drone drone) {
        droneRepository.save(drone);
        return drone;
    }

    public Drone atualizar(Drone drone, long idDrone) {
        Drone droneAnterior = droneRepository.findById(idDrone).orElseThrow(() -> new NotFoundException(notFoundMessage));
        droneAnterior.marca = drone.marca;
        droneAnterior.modelo = drone.modelo;
        droneAnterior.numeroSerie = drone.numeroSerie;
        droneAnterior.capacidade = drone.capacidade;
        droneAnterior.distanciaPorCarga = drone.distanciaPorCarga;
        return droneAnterior;
    }

    public void deletar(long id) {
        droneRepository.deleteById(id);
    }

}
