package io.github.henriquemcc.dtidigital.testepratico.service;

import io.github.henriquemcc.dtidigital.testepratico.exception.NotFoundException;
import io.github.henriquemcc.dtidigital.testepratico.model.Voo;
import io.github.henriquemcc.dtidigital.testepratico.repository.VooRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VooService {
    private VooRepository vooRepository;
    private final String notFoundMessage = "Voo não encontrado";

    public VooService(VooRepository vooRepository) {
        this.vooRepository = vooRepository;
    }

    public List<Voo> listar() {
        return vooRepository.findAll();
    }

    public Voo buscarPorId(long id) {
        return vooRepository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessage));
    }

    public Voo cadastrar(Voo voo) {
        vooRepository.save(voo);
        return voo;
    }

    public Voo atualizar(Voo voo, long idVoo) {
        Voo vooAnterior = vooRepository.findById(idVoo).orElseThrow(() -> new NotFoundException(notFoundMessage));
        vooAnterior.rotas = voo.rotas;
        return vooAnterior;
    }

    public void deletar(long id) {
        vooRepository.deleteById(id);
    }
}
