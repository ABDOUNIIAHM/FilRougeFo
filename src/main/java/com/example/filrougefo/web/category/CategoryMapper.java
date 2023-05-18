package com.example.filrougefo.web.category;

import com.example.filrougefo.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AllCategoriesMapper {
    AllCategoriesDto toDTO(Category category);
    Category fromDTO(AllCategoriesDto categoryDto);
}
