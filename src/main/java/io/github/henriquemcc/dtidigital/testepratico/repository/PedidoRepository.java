package io.github.henriquemcc.dtidigital.testepratico.repository;

import io.github.henriquemcc.dtidigital.testepratico.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByEntregueFalseOrderByPrioridadeDescPesoDesc();
}
