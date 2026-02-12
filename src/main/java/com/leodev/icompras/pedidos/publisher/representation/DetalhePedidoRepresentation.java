package com.leodev.icompras.pedidos.publisher.representation;

import com.leodev.icompras.pedidos.model.enums.StatusPedido;

import java.util.List;

public record DetalhePedidoRepresentation(
        Long codigo,
        Long codigoCliente,
        String nome,
        String cpf,
        String logradouro,
        String numero,
        String bairro,
        String email,
        String telefone,
        String dataPedido,
        String total,
        StatusPedido status,
        List<DetalheItemPedidoRepresentation> itens
) {
}
