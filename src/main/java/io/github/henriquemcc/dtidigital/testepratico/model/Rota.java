package io.github.henriquemcc.dtidigital.testepratico.model;

import jakarta.persistence.*;

@Entity
public class Rota {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

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
