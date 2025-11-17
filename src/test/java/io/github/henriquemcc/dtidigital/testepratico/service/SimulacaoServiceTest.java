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

import static io.github.henriquemcc.dtidigital.testepratico.tmp.JsonPrinter.imprimirComoJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SimulacaoServiceTest {
    private List<Deposito> depositos = List.of(new Deposito("Depósito", new Coordenada(0,0)));
    private List<Pedido> pedidos = List.of(
            new Pedido("Fone de Ouvido", 145.78f, "Descrição detalhada de Fone de Ouvido", 0.1234f, Prioridade.MEDIA, new Coordenada(12, 187)),
            new Pedido("Smart TV", 3567.50f, "Descrição detalhada de Smart TV", 3.4567f, Prioridade.ALTA, new Coordenada(89, 45)),
            new Pedido("Câmera DSLR", 5210.99f, "Descrição detalhada de Câmera DSLR", 1.8765f, Prioridade.BAIXA, new Coordenada(155, 98)),
            new Pedido("Relógio Inteligente", 890.35f, "Descrição detalhada de Relógio Inteligente", 0.0567f, Prioridade.MEDIA, new Coordenada(33, 112)),
            new Pedido("Kit Ferramentas", 256.40f, "Descrição detalhada de Kit Ferramentas", 4.9876f, Prioridade.BAIXA, new Coordenada(199, 1)),
            new Pedido("Teclado Mecânico", 560.15f, "Descrição detalhada de Teclado Mecânico", 1.0123f, Prioridade.ALTA, new Coordenada(76, 76)),
            new Pedido("HD Externo", 450.00f, "Descrição detalhada de HD Externo", 0.4500f, Prioridade.MEDIA, new Coordenada(5, 5)),
            new Pedido("Mochila", 120.90f, "Descrição detalhada de Mochila", 0.8900f, Prioridade.BAIXA, new Coordenada(150, 200)),
            new Pedido("Tênis Esportivo", 340.50f, "Descrição detalhada de Tênis Esportivo", 0.2300f, Prioridade.ALTA, new Coordenada(100, 100)),
            new Pedido("Cafeteira Elétrica", 180.25f, "Descrição detalhada de Cafeteira Elétrica", 2.1000f, Prioridade.BAIXA, new Coordenada(60, 140)),
            new Pedido("Celular", 3200.00f, "Smartphone de última geração", 0.35f, Prioridade.ALTA, new Coordenada(5, 5)),
            new Pedido("Jogo de Pratos", 120.50f, "Conjunto de pratos de cerâmica", 2.1f, Prioridade.BAIXA, new Coordenada(78, 92)),
            new Pedido("Cadeira de Escritório", 950.00f, "Cadeira ergonômica desmontada", 1.8f, Prioridade.MEDIA, new Coordenada(15, 80)),
            new Pedido("Caneta Gel", 5.90f, "Pacote com 10 canetas", 0.01f, Prioridade.BAIXA, new Coordenada(145, 110)),
            new Pedido("Remédio Controlado", 450.00f, "Caixa de medicamentos sob prescrição", 0.7f, Prioridade.ALTA, new Coordenada(30, 20)),
            new Pedido("Saco de Cimento", 35.00f, "Material de construção (saco de 5kg)", 5.0f, Prioridade.MEDIA, new Coordenada(190, 10)),
            new Pedido("Óculos de Sol", 280.00f, "Óculos polarizados", 0.05f, Prioridade.BAIXA, new Coordenada(55, 65)),
            new Pedido("Monitor 27 Polegadas", 1800.00f, "Monitor de computador", 3.5f, Prioridade.ALTA, new Coordenada(180, 175)),
            new Pedido("Cartão de Memória", 45.00f, "Cartão SD 128GB", 0.005f, Prioridade.MEDIA, new Coordenada(12, 160)),
            new Pedido("Impressora 3D", 6000.00f, "Impressora 3D completa", 4.8f, Prioridade.BAIXA, new Coordenada(25, 40))
    );
    private List<Drone> drones = List.of(
            new Drone("MegaDrone Corp", "Titan 5000", "A01", 350.0f, 15.0f),
            new Drone("AeroFreight Systems", "Hercules H-1", "A02", 280.0f, 12.0f),
            new Drone("Swift Delivery", "Medium Hauler", "B03", 80.0f, 25.0f),
            new Drone("SkyLift Logistics", "M-Cargo Pro", "B04", 65.0f, 20.0f),
            new Drone("RoboFly Express", "Mid-Range V2", "B05", 50.0f, 22.0f),
            new Drone("PicoTransports", "Soft Touch", "C06", 15.0f, 40.0f),
            new Drone("Urban Runner", "Quick Dash", "C07", 8.0f, 50.0f),
            new Drone("FeatherWeight Drones", "Lite-Speed", "C08", 5.0f, 60.0f),
            new Drone("OmniFleet", "FlexiLoad 100", "D09", 100.0f, 30.0f),
            new Drone("Global Aviator", "Standard Utility", "D10", 30.0f, 35.0f)
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
        assertEquals(entregas.size(), 1);
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

        // Redirecionamento da chamade de saveAll do vooRepository
        Mockito.doAnswer((Answer<Object>) invocation -> {
            Collection<Voo> voosSalvos = invocation.getArgument(0);
            voos.addAll(voosSalvos);
            return voosSalvos;
        }).when(vooRepository).saveAll(Mockito.anyIterable());

        // Redirecionamento da chamada de save de rotaRepository
        Mockito.doAnswer((Answer<Object>) invocation -> {
            Rota rotaSalva = invocation.getArgument(0);
            rotas.add(rotaSalva);
            return rotaSalva;
        }).when(rotaRepository).save(Mockito.any());
    }


}
