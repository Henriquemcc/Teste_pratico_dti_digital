package io.github.henriquemcc.dtidigital.testepratico.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Coordenada {
    public double x;
    public double y;

    public Coordenada(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coordenada() {

    }
}
