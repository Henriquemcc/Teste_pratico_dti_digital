package io.github.henriquemcc.dtidigital.testepratico.model;

import jakarta.persistence.*;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String nome;
    public float valor;
    public String descricao;
    public float peso;
    @Enumerated(EnumType.STRING)
    public Prioridade prioridade;
    @Embedded
    public Coordenada destino = new Coordenada();
    public boolean entregue = false;

    public Pedido(String nome, float valor, String descricao, float peso, Prioridade prioridade, Coordenada destino) {
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.peso = peso;
        this.prioridade = prioridade;
        this.destino = destino;
    }

    public Pedido() {

    }
}
