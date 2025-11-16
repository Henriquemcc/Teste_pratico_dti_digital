package io.github.henriquemcc.dtidigital.testepratico.controller;

import io.github.henriquemcc.dtidigital.testepratico.model.Entrega;
import io.github.henriquemcc.dtidigital.testepratico.service.EntregaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {
    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @GetMapping
    public List<Entrega> listar() {
        return entregaService.listar();
    }

    @GetMapping("/{id}")
    public Entrega buscarPorId(@PathVariable long id) {
        return entregaService.buscarPorId(id);
    }
}
