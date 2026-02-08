package com.leodev.icompras.pedidos.controller.dto;

import com.leodev.icompras.pedidos.model.enums.TipoPagamento;

public record AdicaoNovoPagamentoDTO(Long codigoPedido, String dados, TipoPagamento tipoPagamento) {
}
