package com.example.filrougefo.web.category;

import com.example.filrougefo.web.Product.ProductMapper;
import com.example.filrougefo.web.Product.ProductMapperImpl;
import org.mapstruct.Mapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class CategoryConfigurationTestController {

    @Bean
    CategoryMapper categoryMapper(){
        return new CategoryMapperImpl();
    }
    @Bean
    ProductMapper productMapper(){
        return new ProductMapperImpl();
    }
}
