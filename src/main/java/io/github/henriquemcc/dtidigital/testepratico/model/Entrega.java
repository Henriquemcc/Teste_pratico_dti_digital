package io.github.henriquemcc.dtidigital.testepratico.model;

import jakarta.persistence.*;

@Entity
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @OneToMany
    public Pedido pedido;

    @ManyToOne
    public Drone drone;

    @OneToOne
    public Voo voo;
}
