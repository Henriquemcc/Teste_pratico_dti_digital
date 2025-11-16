package io.github.henriquemcc.dtidigital.testepratico.controller;

import io.github.henriquemcc.dtidigital.testepratico.model.Rota;
import io.github.henriquemcc.dtidigital.testepratico.service.RotaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rotas")
public class RotaController {
    private final RotaService rotaService;

    public RotaController(RotaService rotaService) {
        this.rotaService = rotaService;
    }

    @GetMapping
    public List<Rota> listar() {
        return rotaService.listar();
    }

    @GetMapping("/{id}")
    public Rota buscarPorId(@PathVariable long id) {
        return rotaService.buscarPorId(id);
    }
}
