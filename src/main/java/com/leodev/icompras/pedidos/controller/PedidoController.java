package com.leodev.icompras.pedidos.controller;

import com.leodev.icompras.pedidos.controller.dto.AdicaoNovoPagamentoDTO;
import com.leodev.icompras.pedidos.controller.dto.NovoPedidoDTO;
import com.leodev.icompras.pedidos.controller.mappers.PedidoMapper;
import com.leodev.icompras.pedidos.model.ErroResposta;
import com.leodev.icompras.pedidos.model.exception.ItemNaoEncontradoException;
import com.leodev.icompras.pedidos.model.exception.ValidationException;
import com.leodev.icompras.pedidos.publisher.DetalhePedidoMapper;
import com.leodev.icompras.pedidos.publisher.representation.DetalhePedidoRepresentation;
import com.leodev.icompras.pedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pedidos")
@RequiredArgsConstructor
@Tag(name = "Pedidos")
public class PedidoController {

    private final PedidoService service;
    private final PedidoMapper mapper;
    private final DetalhePedidoMapper detalhePedidoMapper;

    @PostMapping
    @Operation(summary = "Criar", description = "Criar um novo produto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedido registrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro validação")
    })
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

    @PostMapping("/pagamentos")
    @Operation(summary = "Adicionar Pagamento", description = "Adicionar um novo método de pagamento")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pagamento adicionado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Item não encontrado")
    })
    public ResponseEntity<Object> adicionarNovoPagamento(@RequestBody AdicaoNovoPagamentoDTO dto){
        try {
            service.adicionarNovoPagamento(dto.codigoPedido(), dto.dados(), dto.tipoPagamento());
            return ResponseEntity.noContent().build();
        } catch (ItemNaoEncontradoException e){
            var erro = new ErroResposta("Item não encontrado", "codigoPedido", e.getMessage());
            return ResponseEntity.badRequest().body(erro);
        }
    }

    @GetMapping("{codigo}")
    @Operation(summary = "Obter Detalhes", description = "Retorna os dados de um pedido pelo código")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    public ResponseEntity<DetalhePedidoRepresentation> obterDetalhesPedido(@PathVariable("codigo") Long codigo){
        return service
                .carregarDadosCompletosPedido(codigo)
                .map(detalhePedidoMapper::map)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
