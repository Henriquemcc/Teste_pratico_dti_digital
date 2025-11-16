package io.github.henriquemcc.dtidigital.testepratico.service;

import io.github.henriquemcc.dtidigital.testepratico.exception.NotFoundException;
import io.github.henriquemcc.dtidigital.testepratico.model.Deposito;
import io.github.henriquemcc.dtidigital.testepratico.repository.DepositoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositoService {
    private final DepositoRepository depositoRepository;
    private final String notFoundMessage = "Depósito não encontrado";

    public DepositoService(DepositoRepository depositoRepository) {
        this.depositoRepository = depositoRepository;
    }

    public List<Deposito> listar() {
        return depositoRepository.findAll();
    }

    public Deposito buscarPorId(long id) {
        return depositoRepository.findById(id).orElseThrow(() -> new NotFoundException(notFoundMessage));
    }

    public Deposito cadastrar(Deposito deposito) {
        depositoRepository.save(deposito);
        return deposito;
    }

    public Deposito atualizar(Deposito deposito, long idDeposito) {
        Deposito depositoAnterior = depositoRepository.findById(idDeposito).orElseThrow(() -> new NotFoundException(notFoundMessage));
        depositoAnterior.localizacao = deposito.localizacao;
        return depositoAnterior;
    }

    public void deletar(long id) {
        depositoRepository.deleteById(id);
    }

}
