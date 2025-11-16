package io.github.henriquemcc.dtidigital.testepratico.model;

import jakarta.persistence.*;

@Entity
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public float peso;
    @Enumerated(EnumType.STRING)
    public Prioridade prioridade;
    @Embedded
    public Coordenada coordenada;
    public boolean entregue = false;
}
