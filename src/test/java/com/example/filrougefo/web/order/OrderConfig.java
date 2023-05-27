package com.example.filrougefo.web.order;

import com.example.filrougefo.web.product.MonthMapper;
import com.example.filrougefo.web.product.MonthMapperImpl;
import com.example.filrougefo.web.product.ProductMapper;
import com.example.filrougefo.web.product.ProductMapperImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import com.example.filrougefo.web.order.OrderMapper;
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
    MonthMapper monthMapper(){
        return new MonthMapperImpl();
    }
}
