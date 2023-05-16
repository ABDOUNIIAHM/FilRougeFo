package com.example.filrougefo.web.category;

import com.example.filrougefo.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryProductMapper {
    CategoryProductDto toDTO(Category category);
    Category fromDTO(CategoryProductDto categoryDto);
}
