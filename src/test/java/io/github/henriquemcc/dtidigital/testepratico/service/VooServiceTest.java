package io.github.henriquemcc.dtidigital.testepratico.service;

import io.github.henriquemcc.dtidigital.testepratico.exception.NotFoundException;
import io.github.henriquemcc.dtidigital.testepratico.model.Voo;
import io.github.henriquemcc.dtidigital.testepratico.model.VooTest;
import io.github.henriquemcc.dtidigital.testepratico.repository.VooRepository;
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
public class VooServiceTest {
    private List<Voo> voos = List.of(VooTest.build());

    @Mock
    private VooRepository vooRepository;

    @InjectMocks
    private VooService vooService;

    @Test
    public void deveListarVoos() {
        when(vooRepository.findAll()).thenReturn(voos);
        List<Voo> resultado = vooService.listar();
        assertEquals(resultado, voos);
    }

    @Test
    public void deveLancarNotFoundExceptionQuandoVooNaoForEncontrado() {
        long idInexistente = 999L;
        when(vooRepository.findById(idInexistente)).thenReturn(Optional.empty());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> vooService.buscarPorId(idInexistente));
        assertEquals("Voo não encontrado", thrown.getMessage());
    }
}
