package io.github.henriquemcc.dtidigital.testepratico.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Voo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @OneToMany
    @ElementCollection
    public List<Rota> rotas;
}
