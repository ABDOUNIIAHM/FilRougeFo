package com.example.filrougefo.web.category;

import com.example.filrougefo.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "productList", source = "products")
    CategoryDto toDTO(Category category);
    Category fromDTO(CategoryDto categoryDto);
}
