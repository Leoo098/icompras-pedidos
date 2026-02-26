package com.leodev.icompras.pedidos.service;

import com.leodev.icompras.pedidos.model.enums.StatusPedido;
import com.leodev.icompras.pedidos.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizacaoStatusPedidoService {

    private final PedidoRepository repository;

    @Transactional
    public void atualizarStatus(Long codigo, StatusPedido status, String urlNotaFiscal, String rastreio){
        repository.findById(codigo).ifPresent(pedido -> {
            pedido.setStatus(status);

            if (urlNotaFiscal != null){
                pedido.setUrlNotaFiscal(urlNotaFiscal);
            }

            if (rastreio != null){
                pedido.setCodigoRastreio(rastreio);
            }
        });
    }
}
