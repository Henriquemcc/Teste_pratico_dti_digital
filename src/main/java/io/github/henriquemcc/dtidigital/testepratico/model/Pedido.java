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
    public Coordenada coordenada = new Coordenada();
    public boolean entregue = false;

    public Pedido(float peso, Prioridade prioridade, Coordenada coordenada) {
        this.peso = peso;
        this.prioridade = prioridade;
        this.coordenada = coordenada;
    }

    public Pedido() {

    }
}
