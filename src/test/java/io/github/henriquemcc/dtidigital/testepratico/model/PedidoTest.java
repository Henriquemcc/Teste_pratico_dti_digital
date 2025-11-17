package io.github.henriquemcc.dtidigital.testepratico.model;

public class PedidoTest {
    public static Pedido build() {
        return new Pedido(
                "Livro",
                200.0f,
                "Livro didático",
                0.5f,
                Prioridade.ALTA,
                new Coordenada(10, 15)
        );
    }
}
