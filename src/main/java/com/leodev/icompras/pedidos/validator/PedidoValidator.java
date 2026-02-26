package com.leodev.icompras.pedidos.validator;

import com.leodev.icompras.pedidos.client.ClientesClient;
import com.leodev.icompras.pedidos.client.ProdutosClient;
import com.leodev.icompras.pedidos.client.representation.ClientesRepresentation;
import com.leodev.icompras.pedidos.client.representation.ProdutoRepresentation;
import com.leodev.icompras.pedidos.model.ItemPedido;
import com.leodev.icompras.pedidos.model.Pedido;
import com.leodev.icompras.pedidos.model.exception.ValidationException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoValidator {

    private final ProdutosClient produtosClient;
    private final ClientesClient clientesClient;

    public void validar(Pedido pedido){
        Long codigoCliente = pedido.getCodigoCliente();
        validarCliente(codigoCliente);
        pedido.getItens().forEach(this::validarItem);
    }

    private void validarCliente(Long codigoCliente){
        try {
            var response = clientesClient.obterDados(codigoCliente);
            ClientesRepresentation cliente = response.getBody();
            log.info("Cliente de código {} encontrado: {}", cliente.codigo(), cliente.nome());

            if (!cliente.ativo()){
                throw new ValidationException("codigoCliente", "Cliente inativo.");
            }
        } catch (FeignException.FeignClientException.NotFound e){
            var message = String.format("Cliente de código %d não encontrado.", codigoCliente);
            throw new ValidationException("codigoCliente", message);
        }
    }

    private void validarItem(ItemPedido item){
        try {
            var response = produtosClient.obterDados(item.getCodigoProduto());
            ProdutoRepresentation produto = response.getBody();
            log.info("Produto de código {} encontrado: {}", produto.codigo(), produto.nome());

            if (!produto.ativo()){
                throw new ValidationException("codigoProduto", "Produto inativo.");
            }
        } catch (FeignException.FeignClientException.NotFound e){
            var message = String.format("Produto de código %d não encontrado.", item.getCodigoProduto());
            throw new ValidationException("codigoProduto", message);
        }
    }
}
