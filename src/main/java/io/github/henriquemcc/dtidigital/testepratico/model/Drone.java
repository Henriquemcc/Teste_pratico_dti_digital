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
    public String marca;
    public String modelo;
    public String numeroSerie;
    public float distanciaPorCarga;
    public float capacidade;

    public Drone(String marca, String modelo, String numeroSerie, float distanciaPorCarga, float capacidade) {
        this.marca = marca;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.distanciaPorCarga = distanciaPorCarga;
        this.capacidade = capacidade;
    }

    public Drone() {

    }
}
