package io.github.henriquemcc.dtidigital.testepratico.service;

import io.github.henriquemcc.dtidigital.testepratico.exception.NotFoundException;
import io.github.henriquemcc.dtidigital.testepratico.model.Drone;
import io.github.henriquemcc.dtidigital.testepratico.model.DroneTest;
import io.github.henriquemcc.dtidigital.testepratico.repository.DroneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DroneServiceTest {
    private List<Drone> drones = List.of(DroneTest.build());

    @Mock
    private DroneRepository droneRepository;

    @InjectMocks
    private DroneService droneService;

    @Test
    public void deveListarDrones() {
        when(droneRepository.findAll()).thenReturn(drones);
        List<Drone> resultado = droneService.listar();
        assertEquals(resultado, drones);
    }

    @Test
    public void deveLancarNotFoundExceptionQuandoDroneNaoForAchado() {
        long idInexistente = 999L;
        when(droneRepository.findById(idInexistente)).thenReturn(Optional.empty());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> droneService.buscarPorId(idInexistente));
        assertEquals("Drone não encontrado", thrown.getMessage());

    }
}
