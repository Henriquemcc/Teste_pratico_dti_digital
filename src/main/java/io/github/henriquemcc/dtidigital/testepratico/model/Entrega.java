package io.github.henriquemcc.dtidigital.testepratico.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @OneToMany
    public List<Pedido> pedidos = new ArrayList<>();

    @ManyToOne
    public Drone drone = new Drone();

    @ManyToOne
    public Deposito deposito = new Deposito();

    @ManyToOne
    public Voo voo = new Voo();

    public Entrega(List<Pedido> pedidos, Drone drone, Deposito deposito, Voo voo) {
        this.pedidos = pedidos;
        this.drone = drone;
        this.deposito = deposito;
        this.voo = voo;
    }

    public Entrega() {

    }

    public double getPesoTotal() {
        double pesoTotal = 0;
        if (pedidos != null) for (Pedido pedido : pedidos) {
            pesoTotal += pedido.peso;
        }
        return pesoTotal;
    }
}
