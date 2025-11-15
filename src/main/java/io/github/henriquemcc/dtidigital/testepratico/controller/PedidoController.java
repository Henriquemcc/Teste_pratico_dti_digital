package io.github.henriquemcc.dtidigital.testepratico.controller;

import io.github.henriquemcc.dtidigital.testepratico.model.Pedido;
import io.github.henriquemcc.dtidigital.testepratico.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> listar() {
        return pedidoService.listar();
    }

    @PostMapping
    public Pedido cadastrar(@RequestBody Pedido pedido){
        return pedidoService.cadastrar(pedido);
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable long id) {
        return pedidoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Pedido atualizar(@RequestBody Pedido pedido, @PathVariable long id) {
        return pedidoService.atualizar(pedido, id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable long id) {
        pedidoService.deletar(id);
    }
}
