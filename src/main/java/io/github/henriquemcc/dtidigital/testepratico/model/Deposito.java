package io.github.henriquemcc.dtidigital.testepratico.model;

import jakarta.persistence.*;

@Entity
public class Deposito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Embedded
    public Coordenada localizacao = new Coordenada();

    public Deposito(Coordenada localizacao) {
        this.localizacao = localizacao;
    }

    public Deposito() {

    }
}
