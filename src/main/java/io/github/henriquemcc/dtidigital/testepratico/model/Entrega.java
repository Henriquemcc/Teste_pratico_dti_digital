package io.github.henriquemcc.dtidigital.testepratico.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @OneToMany
    public List<Pedido> pedidos;

    @ManyToOne
    public Drone drone;

    @ManyToOne
    public Voo voo;


    public double getPesoTotal() {
        double pesoTotal = 0;
        for (Pedido pedido: pedidos) {
            pesoTotal += pedido.peso;
        }
        return pesoTotal;
    }
}
