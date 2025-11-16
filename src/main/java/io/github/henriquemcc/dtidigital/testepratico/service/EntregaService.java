package io.github.henriquemcc.dtidigital.testepratico.service;

import io.github.henriquemcc.dtidigital.testepratico.exception.NotFoundException;
import io.github.henriquemcc.dtidigital.testepratico.model.Entrega;
import io.github.henriquemcc.dtidigital.testepratico.repository.EntregaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaService {
    private final EntregaRepository entregaRepository;
    private final String notFoundMessage = "Entrega não encontrada";

    public EntregaService(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    public List<Entrega> listar() {
        return entregaRepository.findAll();
    }

    public Entrega buscarPorId(long id) {
        return entregaRepository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessage));
    }

    public Entrega cadastrar(Entrega entrega) {
        entregaRepository.save(entrega);
        return entrega;
    }

    public Entrega atualizar(Entrega entrega, long idEntrega) {
        Entrega entregaAnterior = entregaRepository.findById(idEntrega).orElseThrow(() -> new NotFoundException(notFoundMessage));
        entregaAnterior.drone = entrega.drone;
        entregaAnterior.voo = entrega.voo;
        entregaAnterior.pedidos = entrega.pedidos;
        return entregaAnterior;
    }

    public void deletar(long id) {
        entregaRepository.deleteById(id);
    }
}
