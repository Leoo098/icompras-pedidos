package com.leodev.icompras.pedidos.client.representation;

public record ClientesRepresentation(
        Long codigo,
        String nome,
        String cpf,
        String logradouro,
        String numero,
        String bairro,
        String email,
        String telefone) {
}
