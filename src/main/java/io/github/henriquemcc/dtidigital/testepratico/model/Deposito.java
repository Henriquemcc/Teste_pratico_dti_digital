package io.github.henriquemcc.dtidigital.testepratico.model;

import jakarta.persistence.*;

@Entity
public class Deposito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String nome;
    @Embedded
    public Coordenada localizacao = new Coordenada();

    public Deposito(String nome, Coordenada localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
    }

    public Deposito() {

    }
}
