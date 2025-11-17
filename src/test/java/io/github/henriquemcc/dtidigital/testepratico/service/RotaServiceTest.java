package io.github.henriquemcc.dtidigital.testepratico.service;

import io.github.henriquemcc.dtidigital.testepratico.exception.NotFoundException;
import io.github.henriquemcc.dtidigital.testepratico.model.Rota;
import io.github.henriquemcc.dtidigital.testepratico.model.RotaTest;
import io.github.henriquemcc.dtidigital.testepratico.repository.RotaRepository;
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
public class RotaServiceTest {
    private List<Rota> rotas = List.of(RotaTest.build());

    @Mock
    private RotaRepository rotaRepository;

    @InjectMocks
    private RotaService rotaService;

    @Test
    public void deveListarRotas() {
        when(rotaRepository.findAll()).thenReturn(rotas);
        List<Rota> resultado = rotaService.listar();
        assertEquals(resultado, rotas);
    }

    @Test
    public void deveLancarNotFoundExceptionQuandoRotaNaoForEncontrada() {
        long idInexistente = 999L;
        when(rotaRepository.findById(idInexistente)).thenReturn(Optional.empty());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> rotaService.buscarPorId(idInexistente));
        assertEquals("Rota não encontrada", thrown.getMessage());
    }
}
