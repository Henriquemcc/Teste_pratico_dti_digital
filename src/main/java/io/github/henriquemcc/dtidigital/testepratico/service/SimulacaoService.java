package io.github.henriquemcc.dtidigital.testepratico.service;

import io.github.henriquemcc.dtidigital.testepratico.model.*;
import io.github.henriquemcc.dtidigital.testepratico.repository.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class SimulacaoService {
    private final PedidoRepository pedidoRepository;
    private final EntregaRepository entregaRepository;
    private final VooRepository vooRepository;
    private final DroneRepository droneRepository;
    private final DepositoRepository depositoRepository;
    private final RotaRepository rotaRepository;

    public SimulacaoService(PedidoRepository pedidoRepository, EntregaRepository entregaRepository, VooRepository vooRepository, DroneRepository droneRepository, DepositoRepository depositoRepository, RotaRepository rotaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.entregaRepository = entregaRepository;
        this.vooRepository = vooRepository;
        this.droneRepository = droneRepository;
        this.depositoRepository = depositoRepository;
        this.rotaRepository = rotaRepository;
    }

    private static double calcularDistancia(Coordenada origem, Coordenada destino) {
        return Math.sqrt(Math.pow(destino.x - origem.x, 2) + Math.pow(destino.y - origem.y, 2));
    }

    public void executarSimulacao() {
        // Obtendo todos os pedidos que não foram entregues ordenados pela prioridade
        List<Pedido> pedidosPendentes = carregarPedidosPendentes();

        // Obtendo todos os drones ordenados pela capacidade
        List<Drone> drones = carregarDronesOrdenados();


        // Obtendo o depósito
        Deposito deposito = carregarDeposito();

        // Alocando pedidos em drones
        List<Entrega> entregas = montarEntregas(pedidosPendentes, drones, deposito);
        ArrayList<Voo> voos = gerarVoos(entregas, deposito);

        // Salva tudo automaticamente graças ao cascade
        salvarResultados(voos, entregas);
    }

    private void salvarResultados(ArrayList<Voo> voos, List<Entrega> entregas) {
        vooRepository.saveAll(voos);
        entregaRepository.saveAll(entregas);
    }

    private ArrayList<Voo> gerarVoos(List<Entrega> entregas, Deposito deposito) {
        ArrayList<Voo> voos = new ArrayList<>();


        // Calculando rotas para o voo
        for (Entrega entrega: entregas)
        {
            ArrayList<Rota> rotas = new ArrayList<>();
            Coordenada anterior = deposito.localizacao;
            for (Pedido pedido: entrega.pedidos) {
                Rota rota = new Rota();
                rota.origem = anterior;
                rota.destino = pedido.destino;
                rotaRepository.save(rota);
                rotas.add(rota);
                anterior = pedido.destino;
            }

            // Volta ao depósito
            Rota rotaFinal = new Rota();
            rotaFinal.origem = anterior;
            rotaFinal.destino = deposito.localizacao;
            rotaRepository.save(rotaFinal);
            rotas.add(rotaFinal);

            // Criando o voo
            Voo voo = new Voo();
            voo.rotas.addAll(rotas);

            entrega.voo = voo;
            voos.add(voo);
        }
        return voos;
    }

    private List<Entrega> montarEntregas(List<Pedido> pedidosPendentes, List<Drone> drones, Deposito deposito) {

        // Separando as prioridades
        List<Pedido> altas = pedidosPendentes.stream().filter(p -> p.prioridade == Prioridade.ALTA).toList();
        List<Pedido> medias = pedidosPendentes.stream().filter(p -> p.prioridade == Prioridade.MEDIA).toList();
        List<Pedido> baixas = pedidosPendentes.stream().filter(p -> p.prioridade == Prioridade.BAIXA).toList();

        // Criando uma lista ordenada
        LinkedList<Pedido> listaOrdenada = new LinkedList<>();
        listaOrdenada.addAll(altas);
        listaOrdenada.addAll(medias);
        listaOrdenada.addAll(baixas);

        // Criando lista de entregas
        ArrayList<Entrega> entregas = new ArrayList<>();

        while (!listaOrdenada.isEmpty())
        {
            Pedido pedido = listaOrdenada.removeFirst();
            boolean pedidoAlocado = false;

            // tentando colocar o pedido em entregas existentes, mantendo a ordem de prioridade
            for (Entrega entrega: entregas) {
                // Verificando se o drone cabe o peso
                boolean droneCabePeso = entrega.getPesoTotal() + pedido.peso <= entrega.drone.capacidade;

                // Verificando se a distância é compatível com o drone
                boolean droneCabeDistancia = calcularDistancia(deposito.localizacao, pedido.destino) <= entrega.drone.distanciaPorCarga;

                if (droneCabePeso && droneCabeDistancia) {
                    entrega.pedidos.add(pedido);
                    pedido.entregue = true;
                    pedidoAlocado = true;
                    break;
                }
            }

            // Se não couber em nenhuma entrega existente, criar uma nova entrega
            if (!pedidoAlocado) {
                Entrega nova = new Entrega();
                nova.deposito = deposito;

                // Escolhendo drone com suporte ao peso e ao alcance
                for (Drone d: drones) {

                    // Verificando se o drone cabe o peso
                    boolean droneCabePeso = d.capacidade >= pedido.peso;

                    // Verificando se o drone cabe a distância
                    boolean droneCabeDistancia = calcularDistancia(deposito.localizacao, pedido.destino) <= d.distanciaPorCarga;

                    if (droneCabePeso && droneCabeDistancia) {
                        nova.drone = d;
                        break;
                    }
                }

                nova.pedidos.add(pedido);
                pedido.entregue = true;
                entregas.add(nova);
            }
        }
        return entregas;
    }

    private Deposito carregarDeposito() {
        return depositoRepository.findAll().getFirst();
    }

    private List<Drone> carregarDronesOrdenados() {
        return droneRepository.findAll(Sort.by(Sort.Direction.DESC, "capacidade"));
    }

    private List<Pedido> carregarPedidosPendentes() {
        return pedidoRepository.findByEntregueFalseOrderByPrioridadeDescPesoDesc();
    }
}
