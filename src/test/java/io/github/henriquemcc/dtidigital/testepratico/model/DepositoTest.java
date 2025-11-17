package io.github.henriquemcc.dtidigital.testepratico.model;

public class DepositoTest {
    public static Deposito build() {
        return new Deposito(
                "Depósito 1",
                new Coordenada(0, 0)
        );
    }
}
