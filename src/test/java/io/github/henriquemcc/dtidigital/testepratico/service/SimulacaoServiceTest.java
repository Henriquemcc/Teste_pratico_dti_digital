package io.github.henriquemcc.dtidigital.testepratico.service;

import io.github.henriquemcc.dtidigital.testepratico.model.*;
import io.github.henriquemcc.dtidigital.testepratico.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SimulacaoServiceTest {
    private List<Deposito> depositos = List.of(new Deposito("Depósito", new Coordenada(0,0)));
    private List<Pedido> pedidos = List.of(
            new Pedido("Tenis", 500.0f, "Tenis de corrida", 0.001f, Prioridade.BAIXA, new Coordenada(10, 15)),
            new Pedido("Calça", 500.0f, "Calça Jeans", 0.001f, Prioridade.BAIXA, new Coordenada(40, 14)),
            new Pedido("Meia", 500.0f, "Meia", 0.001f, Prioridade.BAIXA, new Coordenada(13, 24)),
            new Pedido("Livro", 200.0f, "Livro didático", 0.3f, Prioridade.MEDIA, new Coordenada(15, 16)),
            new Pedido("Computador", 5000.0f, "Laptop", 0.3f, Prioridade.ALTA, new Coordenada(26, 32)),
            new Pedido("Lápis", 14, "Lápis", 0.0001f, Prioridade.BAIXA, new Coordenada(100, 24)),
            new Pedido("Pão de queijo", 3, "Pão de queijo congelado", 0.001f, Prioridade.ALTA, new Coordenada(123, 132))
    );
    private List<Drone> drones = List.of(
            new Drone("Drone INC", "Heavy Load", "1", 200, 10),
            new Drone("Drone INC", "Soft Load", "2", 10, 5),
            new Drone("Drone INC", "Medium Load", "3", 50, 7)
    );

    private ArrayList<Entrega> entregas = new ArrayList<>();
    private ArrayList<Voo> voos = new ArrayList<>();
    private ArrayList<Rota> rotas = new ArrayList<>();

    @Mock
    private DepositoRepository depositoRepository;
    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private DroneRepository droneRepository;
    @Mock
    private EntregaRepository entregaRepository;
    @Mock
    private VooRepository vooRepository;
    @Mock
    private RotaRepository rotaRepository;

    @InjectMocks
    private SimulacaoService simulacaoService;

    @Test
    public void deveRealizarSomenteUmaEntrega() {
        redirecionarChamadasRepositories();
        simulacaoService.executarSimulacao();
        System.out.println(entregas);
    }

    private void redirecionarChamadasRepositories() {
        when(depositoRepository.findAll()).thenReturn(depositos);
        when(pedidoRepository.findByEntregueFalseOrderByPrioridadeDescPesoDesc()).thenReturn(pedidos);
        when(droneRepository.findAll(Mockito.any(Sort.class))).thenReturn(drones);

        // Redirecionamento da chamada de saveAll do entregaRepository
        Mockito.doAnswer((Answer<Object>) invocation -> {
            Collection<Entrega> entregasSalvas = invocation.getArgument(0);
            entregas.addAll(entregasSalvas);
            return entregasSalvas;

        }).when(entregaRepository).saveAll(Mockito.anyIterable());

        // Redirecionamento da chamada de save do entregaRepository
        Mockito.doAnswer((Answer<Object>) invocation -> {
            Entrega entregaSalva = invocation.getArgument(0);
            entregas.add(entregaSalva);
            return entregaSalva;
        }).when(entregaRepository).save(Mockito.any());

        // Redirecionamento da chamade de saveAll do vooRepository
        Mockito.doAnswer((Answer<Object>) invocation -> {
            Collection<Voo> voosSalvos = invocation.getArgument(0);
            voos.addAll(voosSalvos);
            return voosSalvos;
        }).when(vooRepository).saveAll(Mockito.anyIterable());

        // Redirecionamento da chamada de save de vooRepository
        Mockito.doAnswer((Answer<Object>) invocation -> {
            Voo vooSalvo = invocation.getArgument(0);
            voos.add(vooSalvo);
            return vooSalvo;
        }).when(vooRepository).save(Mockito.any());

        // Redirecionamento da chamade de saveAll do rotaRepository
        Mockito.doAnswer((Answer<Object>) invocation -> {
           Collection<Rota> rotasSalvas = invocation.getArgument(0);
           rotas.addAll(rotasSalvas);
           return rotasSalvas;
        }).when(rotaRepository).saveAll(Mockito.anyIterable());

        // Redirecionamento da chamada de save de rotaRepository
        Mockito.doAnswer((Answer<Object>) invocation -> {
            Rota rotaSalva = invocation.getArgument(0);
            rotas.add(rotaSalva);
            return rotaSalva;
        }).when(rotaRepository).save(Mockito.any());
    }


}
