package io.github.henriquemcc.dtidigital.testepratico.service;

import io.github.henriquemcc.dtidigital.testepratico.model.*;
import io.github.henriquemcc.dtidigital.testepratico.repository.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimulacaoService {
    private final PedidoRepository pedidoRepository;
    private final EntregaRepository entregaRepository;
    private final VooRepository vooRepository;
    private final DroneRepository droneRepository;
    private final DepositoRepository depositoRepository;

    public SimulacaoService(PedidoRepository pedidoRepository, EntregaRepository entregaRepository, VooRepository vooRepository, DroneRepository droneRepository, DepositoRepository depositoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.entregaRepository = entregaRepository;
        this.vooRepository = vooRepository;
        this.droneRepository = droneRepository;
        this.depositoRepository = depositoRepository;
    }

    private static double calcularDistancia(Coordenada origem, Coordenada destino) {
        return Math.sqrt(Math.pow(destino.x - origem.x, 2) + Math.pow(destino.y - origem.y, 2));
    }

    public void executarSimulacao() {
        // Obtendo todos os pedidos que não foram entregues ordenados pela prioridade
        List<Pedido> pedidosPendentes = pedidoRepository.findByEntregueFalseOrderByPrioridadeDescPesoDesc();

        // Obtendo todos os drones ordenados pela capacidade
        List<Drone> drones = droneRepository.findAll(Sort.by(Sort.Direction.DESC, "capacidade"));


        // Obtendo o depósito
        Deposito deposito = depositoRepository.findAll().getFirst();

        // Alocando pedidos em drones
        ArrayList<Entrega> entregas = new ArrayList<>();
        ArrayList<Voo> voos = new ArrayList<>();
        int droneIndex = 0;
        while (!pedidosPendentes.isEmpty()) {
            Pedido pedido = pedidosPendentes.removeFirst();

            // Caso a lista de entregas esteja vazia, criando uma nova entrega
            if (entregas.isEmpty()) {
                Entrega entrega = new Entrega();
                entrega.drone = drones.get(droneIndex);
                entrega.deposito = deposito;
                droneIndex = (droneIndex + 1) % drones.size();
                entregas.add(entrega);
            }


            // Alocando os pedidos para o primeiro drone que encontrar com espaço necessário
            boolean pedidoAlocado = false;


            // Tentando alocar o pedido em uma entrega existente
            for (Entrega entrega : entregas) {
                if (entrega.getPesoTotal() + pedido.peso <= entrega.drone.capacidade && calcularDistancia(deposito.localizacao, pedido.coordenada) <= entrega.drone.distanciaPorCarga) {
                    entrega.pedidos.add(pedido);
                    pedido.entregue = true;
                    pedidoAlocado = true;
                    break;
                }
            }

            // Caso o pedido não tenha sido alocado em uma entrega existente, criando uma nova entrega
            if (!pedidoAlocado) {
                Entrega entrega = new Entrega();
                entrega.deposito = deposito;

                // Escolhendo um drone que tenha capacidade para a entrega
                for (int i = droneIndex; i >= 0; i--) {
                    Drone drone = drones.get(i);
                    if (drone.capacidade >= pedido.peso && calcularDistancia(deposito.localizacao, pedido.coordenada) <= drone.distanciaPorCarga) {
                        entrega.drone = drone;
                        break;
                    }
                }

                // Adicionando pedido á entrega
                entrega.pedidos.add(pedido);
                pedido.entregue = true;

                // Adicionando nova entrega
                entregas.add(entrega);
            }
        }

        // Calculando rotas para o voo
        for (Entrega entrega: entregas)
        {
            ArrayList<Rota> rotas = new ArrayList<>();
            Coordenada anterior = deposito.localizacao;
            Coordenada atual = deposito.localizacao;
            for (Pedido pedido: entrega.pedidos) {

                // Obtendo a rota de destino de entrega
                atual = pedido.coordenada;

                // Adicionando a rota do ponto anterior até o destino da entrega
                Rota rota = new Rota();
                rota.origem = anterior;
                rota.destino = atual;
                rotas.add(rota);

                // Alterando o valor da rota anterior para o da atual
                anterior = atual;
            }

            // Adicionando a rota para voltar para o depósito
            atual = deposito.localizacao;
            Rota rota = new Rota();
            rota.origem = anterior;
            rota.destino = atual;
            rotas.add(rota);

            // Adicionando rotas ao voo
            entrega.voo.rotas = rotas;
        }

        // Adicionando entregas e voos criados
        entregaRepository.saveAll(entregas);
        vooRepository.saveAll(voos);
    }
}
