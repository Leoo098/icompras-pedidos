package com.leodev.icompras.pedidos.model;

import com.leodev.icompras.pedidos.model.enums.TipoPagamento;
import lombok.Data;

@Data
public class DadosPagamento {
    private String dados;
    private TipoPagamento tipoPagamento;
}
