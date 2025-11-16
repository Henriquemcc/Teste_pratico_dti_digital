package io.github.henriquemcc.dtidigital.testepratico.controller;

import io.github.henriquemcc.dtidigital.testepratico.model.Deposito;
import io.github.henriquemcc.dtidigital.testepratico.service.DepositoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depositos")
public class DepositoController {
    private final DepositoService depositoService;

    public DepositoController(DepositoService depositoService) {
        this.depositoService = depositoService;
    }

    @GetMapping
    public List<Deposito> listar() {
        return depositoService.listar();
    }

    @GetMapping("/{id}")
    public Deposito buscarPorId(@PathVariable long id) {
        return depositoService.buscarPorId(id);
    }

    @PostMapping
    public Deposito cadastrar(@RequestBody Deposito deposito) {
        return depositoService.cadastrar(deposito);
    }

    @PutMapping("/{id}")
    public Deposito atualizar(@RequestBody Deposito deposito, @PathVariable long id) {
        return depositoService.atualizar(deposito, id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable long id) {
        depositoService.deletar(id);
    }

}
