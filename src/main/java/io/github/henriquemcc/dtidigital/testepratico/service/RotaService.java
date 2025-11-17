package io.github.henriquemcc.dtidigital.testepratico.service;

import io.github.henriquemcc.dtidigital.testepratico.exception.NotFoundException;
import io.github.henriquemcc.dtidigital.testepratico.model.Rota;
import io.github.henriquemcc.dtidigital.testepratico.repository.RotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RotaService {
    private final RotaRepository rotaRepository;
    private final String notFoundMessage = "Rota não encontrada";

    public RotaService(RotaRepository rotaRepository) {
        this.rotaRepository = rotaRepository;
    }

    public List<Rota> listar() {
        return rotaRepository.findAll();
    }

    public Rota buscarPorId(long id) {
        return rotaRepository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessage));
    }

    public Rota cadastrar(Rota rota) {
        rotaRepository.save(rota);
        return rota;
    }

    public Rota atualizar(Rota rota, long idRota) {
        Rota rotaAnterior = rotaRepository.findById(idRota).orElseThrow(() -> new NotFoundException(notFoundMessage));
        rotaAnterior.destino = rota.destino;
        rotaAnterior.origem = rota.origem;
        return rotaAnterior;
    }

    public void deletar(long id) {
        rotaRepository.deleteById(id);
    }


}
