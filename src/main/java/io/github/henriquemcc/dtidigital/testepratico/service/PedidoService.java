package io.github.henriquemcc.dtidigital.testepratico.service;

import io.github.henriquemcc.dtidigital.testepratico.exception.NotFoundException;
import io.github.henriquemcc.dtidigital.testepratico.model.Pedido;
import io.github.henriquemcc.dtidigital.testepratico.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    private PedidoRepository pedidoRepository;
    private final String notFoundMessage = "Pedido não encontrado";

    public PedidoService(PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listar(){
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(long id){
        return pedidoRepository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessage));
    }

    public Pedido cadastrar(Pedido pedido) {
        pedidoRepository.save(pedido);
        return pedido;
    }

    public Pedido atualizar(Pedido pedido, long idPedido) {
        Pedido pedidoAnterior = pedidoRepository.findById(idPedido).orElseThrow(() -> new NotFoundException(notFoundMessage));
        pedidoAnterior.nome = pedido.nome;
        pedidoAnterior.valor = pedido.valor;
        pedidoAnterior.descricao = pedido.descricao;
        pedidoAnterior.destino = pedido.destino;
        pedidoAnterior.peso = pedido.peso;
        pedidoAnterior.prioridade = pedido.prioridade;
        return pedidoAnterior;
    }

    public void deletar(long id) {
        pedidoRepository.deleteById(id);
    }
}
