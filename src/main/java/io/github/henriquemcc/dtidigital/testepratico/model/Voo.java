package io.github.henriquemcc.dtidigital.testepratico.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Voo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @OneToMany
    @JoinTable(
            name = "voo_rotas",
            joinColumns = @JoinColumn(name = "voo_id"),
            inverseJoinColumns = @JoinColumn(name = "rota_id")
    )
    public List<Rota> rotas = new ArrayList<>();

    public Voo(List<Rota> rotas) {
        this.rotas = rotas;
    }

    public Voo() {

    }
}
