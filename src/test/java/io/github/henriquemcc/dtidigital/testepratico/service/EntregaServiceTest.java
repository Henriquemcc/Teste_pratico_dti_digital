package io.github.henriquemcc.dtidigital.testepratico.service;

import io.github.henriquemcc.dtidigital.testepratico.exception.NotFoundException;
import io.github.henriquemcc.dtidigital.testepratico.model.Entrega;
import io.github.henriquemcc.dtidigital.testepratico.model.EntregaTest;
import io.github.henriquemcc.dtidigital.testepratico.repository.EntregaRepository;
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
public class EntregaServiceTest {
    private List<Entrega> entregas = List.of(EntregaTest.build());

    @Mock
    private EntregaRepository entregaRepository;

    @InjectMocks
    private EntregaService entregaService;

    @Test
    public void deveListarEntregas() {
        when(entregaRepository.findAll()).thenReturn(entregas);
        List<Entrega> resultado = entregaService.listar();
        assertEquals(resultado, entregas);
    }

    @Test
    public void deveLancarNotFoundExceptionQuandoEntregaNaoForEncontrada() {
        long idInexistente = 999L;
        when(entregaRepository.findById(idInexistente)).thenReturn(Optional.empty());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> entregaService.buscarPorId(idInexistente));
        assertEquals("Entrega não encontrada", thrown.getMessage());
    }
}
