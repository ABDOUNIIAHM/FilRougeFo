package com.example.filrougefo.web.category;

import com.example.filrougefo.entity.Category;
import com.example.filrougefo.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "countProduct", expression = "java(CategoryMapper.getListSize(category.getProducts()))")
    @Mapping(target = "productList", source = "products")
    CategoryDto toDTO(Category category);
    @Mapping(target = "products", ignore = true)
    Category fromDTO(CategoryDto categoryDto);

    static int getListSize(List<Product> list) {
        return list != null ? list.size() : 0;
    }
}
