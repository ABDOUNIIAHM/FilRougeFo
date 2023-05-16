package com.example.filrougefo.web.Product;

import com.example.filrougefo.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDTO(Product Product);
    @Mapping(target = "stock", ignore = true)
    @Mapping(target = "orderLines", ignore = true)
    Product fromDTO(ProductDTO ProductDTO);
}
