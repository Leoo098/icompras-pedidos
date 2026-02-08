package com.leodev.icompras.pedidos.controller.mappers;

import com.leodev.icompras.pedidos.controller.dto.ItemPedidoDTO;
import com.leodev.icompras.pedidos.model.ItemPedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {
    ItemPedido map(ItemPedidoDTO dto);
}
