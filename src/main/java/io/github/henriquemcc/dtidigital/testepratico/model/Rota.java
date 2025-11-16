package io.github.henriquemcc.dtidigital.testepratico.model;

import jakarta.persistence.*;

@Embeddable
public class Rota {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="x", column = @Column(name = "origem_x")),
            @AttributeOverride(name="y", column = @Column(name = "origem_y"))
    })
    public Coordenada origem;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="x", column = @Column(name = "destino_x")),
            @AttributeOverride(name="y", column = @Column(name = "destino_y"))
    })
    public Coordenada destino;
}
