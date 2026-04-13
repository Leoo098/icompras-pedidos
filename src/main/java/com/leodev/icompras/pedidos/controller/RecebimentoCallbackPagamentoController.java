package com.leodev.icompras.pedidos.controller;

import com.leodev.icompras.pedidos.controller.dto.RecebimentoCallbackPagamentoDTO;
import com.leodev.icompras.pedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/callback-pagamentos")
@RequiredArgsConstructor
@Tag(name = "Callback de Pagamentos")
public class RecebimentoCallbackPagamentoController {

    private final PedidoService pedidoService;

    @PostMapping
    @Operation(summary = "Atualizar Status", description = "Atualiza o status do pagamento")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status de pagamento atualizado.")
    })
    public ResponseEntity<Object> atualizarStatusPagamento(
            @RequestBody RecebimentoCallbackPagamentoDTO body,
            @RequestHeader(required = true, name = "apiKey") String apiKey){

        pedidoService.atualizarStatusPagamento(body.codigo(),
                body.chavePagamento(),
                body.status(),
                body.observacoes());

        return ResponseEntity.ok().build();
    }
}
