package io.github.henriquemcc.dtidigital.testepratico.model;

import java.util.List;

public class EntregaTest {
    public static Entrega build() {
        Pedido pedido = PedidoTest.build();
        Drone drone = DroneTest.build();
        Deposito deposito = DepositoTest.build();
        Voo voo = VooTest.build();
        return new Entrega(List.of(pedido), drone, deposito, voo);
    }
}
