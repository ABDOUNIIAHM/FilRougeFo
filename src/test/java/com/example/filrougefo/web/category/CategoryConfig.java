package com.example.filrougefo.web.category;

import com.example.filrougefo.web.Product.ProductMapper;
import com.example.filrougefo.web.Product.ProductMapperImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class CategoryConfig {
    @Bean
    CategoryMapper categoryMapper(){
        return new CategoryMapperImpl();
    }
    @Bean
    ProductMapper productMapper(){
        return new ProductMapperImpl();
    }
}
