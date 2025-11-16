package io.github.henriquemcc.dtidigital.testepratico.controller;

import io.github.henriquemcc.dtidigital.testepratico.model.Voo;
import io.github.henriquemcc.dtidigital.testepratico.service.VooService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/voos")
public class VooController {
    private final VooService vooService;

    public VooController(VooService vooService) {
        this.vooService = vooService;
    }

    @GetMapping
    public List<Voo> listar() {
        return vooService.listar();
    }

    @GetMapping("/{id}")
    public Voo buscarPorId(@PathVariable long id) {
        return vooService.buscarPorId(id);
    }
}
