package com.leodev.icompras.pedidos.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfig {

    @Bean
    public NewTopic pedidoPagoTopic(){
        return TopicBuilder.name("icompras.pedidos-pagos")
                .partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic pedidoFaturadoTopic(){
        return TopicBuilder.name("icompras.pedidos-faturados")
                .partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic pedidoEnviadoTopic(){
        return TopicBuilder.name("icompras.pedidos-enviados")
                .partitions(1).replicas(1).build();
    }
}
