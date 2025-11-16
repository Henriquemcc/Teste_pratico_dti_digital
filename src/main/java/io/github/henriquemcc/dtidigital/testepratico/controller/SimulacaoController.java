package io.github.henriquemcc.dtidigital.testepratico.controller;

import io.github.henriquemcc.dtidigital.testepratico.service.SimulacaoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simulacao")
public class SimulacaoController {
    private final SimulacaoService simulacaoService;

    public SimulacaoController(SimulacaoService simulacaoService) {
        this.simulacaoService = simulacaoService;
    }

    @PostMapping
    public void executar() {
        simulacaoService.executarSimulacao();
    }
}
