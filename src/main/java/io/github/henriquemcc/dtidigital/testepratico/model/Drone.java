package io.github.henriquemcc.dtidigital.testepratico.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public float distanciaPorCarga;
    public float capacidade;
}
