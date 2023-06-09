package com.example.filrougefo.web.order;

import com.example.filrougefo.web.client.AddressMapper;
import com.example.filrougefo.web.client.AddressMapperImpl;
import com.example.filrougefo.web.product.MonthMapper;
import com.example.filrougefo.web.product.MonthMapperImpl;
import com.example.filrougefo.web.product.ProductMapper;
import com.example.filrougefo.web.product.ProductMapperImpl;

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
    @Bean
    ProductMapper productMapper(){
        return new ProductMapperImpl();
    }
    @Bean
    AddressMapper addressMapper(){
        return new AddressMapperImpl();
    }

    @Bean
    MonthMapper monthMapper(){
        return new MonthMapperImpl();
    }
}
