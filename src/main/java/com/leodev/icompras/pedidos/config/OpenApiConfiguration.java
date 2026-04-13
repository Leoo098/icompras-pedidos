package com.leodev.icompras.pedidos.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "ICompras Pedidos API",
                version = "v1",
                contact = @Contact(
                        name = "Leonardo Ribeiro",
                        url = "https://github.com/Leoo098/icompras-pedidos"
                )
        )
)
public class OpenApiConfiguration {
}
