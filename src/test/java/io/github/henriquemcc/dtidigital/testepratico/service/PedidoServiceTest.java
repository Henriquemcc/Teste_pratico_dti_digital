package io.github.henriquemcc.dtidigital.testepratico.service;

import io.github.henriquemcc.dtidigital.testepratico.exception.NotFoundException;
import io.github.henriquemcc.dtidigital.testepratico.model.Pedido;
import io.github.henriquemcc.dtidigital.testepratico.model.PedidoTest;
import io.github.henriquemcc.dtidigital.testepratico.repository.PedidoRepository;
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
public class PedidoServiceTest {
    private List<Pedido> pedidos = List.of(PedidoTest.build());

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    public void deveListarPedidos() {
        when(pedidoRepository.findAll()).thenReturn(pedidos);
        List<Pedido> resultado = pedidoService.listar();
        assertEquals(resultado, pedidos);
    }

    @Test
    public void deveLancarNotFoundExceptionQuandoPedidoNaoForEncontrado() {
        long idInexistente = 999L;
        when(pedidoRepository.findById(idInexistente)).thenReturn(Optional.empty());
        NotFoundException thrown = assertThrows(NotFoundException.class, () -> pedidoService.buscarPorId(idInexistente));
        assertEquals("Pedido não encontrado", thrown.getMessage());
    }
}
