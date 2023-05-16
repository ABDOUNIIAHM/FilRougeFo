package com.example.filrougefo.web.category;

import com.example.filrougefo.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryProductMapper {
    @Mapping(target = "productList", source = "products")
    CategoryProductDto toDTO(Category category);
    Category fromDTO(CategoryProductDto categoryDto);
}
