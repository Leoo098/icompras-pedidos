package com.leodev.icompras.pedidos.controller.dto;

import com.leodev.icompras.pedidos.model.enums.TipoPagamento;

public record DadosPagamentoDTO(String dados,
                                TipoPagamento tipoPagamento) {
}
