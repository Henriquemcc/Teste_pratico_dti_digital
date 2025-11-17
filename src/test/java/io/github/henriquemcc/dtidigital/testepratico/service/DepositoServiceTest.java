package io.github.henriquemcc.dtidigital.testepratico.service;

import io.github.henriquemcc.dtidigital.testepratico.exception.NotFoundException;
import io.github.henriquemcc.dtidigital.testepratico.model.Deposito;
import io.github.henriquemcc.dtidigital.testepratico.model.DepositoTest;
import io.github.henriquemcc.dtidigital.testepratico.repository.DepositoRepository;
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
public class DepositoServiceTest {
    private List<Deposito> depositos = List.of(DepositoTest.build());

    @Mock
    private DepositoRepository depositoRepository;

    @InjectMocks
    private DepositoService depositoService;

    @Test
    public void deveListarDepositos() {
        when(depositoRepository.findAll()).thenReturn(depositos);
        List<Deposito> resultado = depositoService.listar();
        assertEquals(resultado, depositos);
    }

    @Test
    public void deveLancarNotFoundExceptionQuandoDepositoNaoForAchado() {
        long idInexistente = 999L;
        when(depositoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> depositoService.buscarPorId(idInexistente));

        assertEquals("Depósito não encontrado", thrown.getMessage());
    }
}
