package io.github.henriquemcc.dtidigital.testepratico.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Deposito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Embedded
    public Coordenada localizacao;
}
