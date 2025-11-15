package io.github.henriquemcc.dtidigital.testepratico.controller;

import io.github.henriquemcc.dtidigital.testepratico.model.Drone;
import io.github.henriquemcc.dtidigital.testepratico.service.DroneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drones")
public class DroneController {
    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @GetMapping
    public List<Drone> listar(){
        return droneService.listar();
    }

    @GetMapping("/{id}")
    public Drone buscarPorId(@PathVariable long id){
        return droneService.buscarPorId(id);
    }

    @PostMapping
    public Drone cadastrar(@RequestBody Drone drone) {
        return droneService.cadastrar(drone);
    }

    @PutMapping("/{id}")
    public Drone atualizar(@RequestBody Drone drone, @PathVariable long id) {
        return droneService.atualizar(drone, id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable long id){
        droneService.deletar(id);
    }
}
