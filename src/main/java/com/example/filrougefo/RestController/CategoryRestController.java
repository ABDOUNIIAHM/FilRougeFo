package com.example.filrougefo.RestController;

import com.example.filrougefo.entity.Category;
import com.example.filrougefo.service.category.IntCategoryService;
import com.example.filrougefo.web.category.CategoryProductDto;
import com.example.filrougefo.web.category.CategoryProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryRestController {
    private final IntCategoryService categoryService;
    private CategoryProductMapper categoryProductMapper;

    @GetMapping("/{id}")
    public CategoryProductDto fetchAllCategorys(@PathVariable int id){

        Category category = categoryService.findById(id);


        CategoryProductDto categoryProductDto = categoryProductMapper.toDTO(category);


        return categoryProductDto;
    }
}
