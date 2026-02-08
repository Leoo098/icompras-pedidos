package com.leodev.icompras.pedidos.repository;

import com.leodev.icompras.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
