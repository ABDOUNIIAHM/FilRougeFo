package com.example.filrougefo.web.product;

import com.example.filrougefo.web.category.CategoryMapper;
import com.example.filrougefo.web.category.CategoryMapperImpl;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ProductConfig {
    @Bean
    CategoryMapper categoryMapper(){
        return new CategoryMapperImpl();
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
