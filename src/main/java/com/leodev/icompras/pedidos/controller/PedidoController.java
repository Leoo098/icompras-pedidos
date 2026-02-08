package com.leodev.icompras.pedidos.controller;

import com.leodev.icompras.pedidos.controller.dto.NovoPedidoDTO;
import com.leodev.icompras.pedidos.controller.mappers.PedidoMapper;
import com.leodev.icompras.pedidos.model.ErroResposta;
import com.leodev.icompras.pedidos.model.exception.ValidationException;
import com.leodev.icompras.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService service;
    private final PedidoMapper mapper;

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody NovoPedidoDTO dto){
        try {
            var pedido = mapper.map(dto);
            var novoPedido = service.criarPedido(pedido);
            return ResponseEntity.ok(novoPedido.getCodigo());
        }catch (ValidationException e){
            var erro = new ErroResposta("Erro validação", e.getField(), e.getMessage());
            return ResponseEntity.badRequest().body(erro);
        }
    }
}
