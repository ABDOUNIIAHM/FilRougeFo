package com.example.filrougefo.web.order;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
@TestConfiguration
public class OrderConfig {
    @Bean
    OrderMapper orderMapper(){
        return new OrderMapperImpl();
    }
    @Bean
    OrderLineMapper orderLineMapper(){
        return new OrderLineMapperImpl();
    }
}
