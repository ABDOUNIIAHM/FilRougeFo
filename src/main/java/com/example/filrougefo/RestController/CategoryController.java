package com.example.filrougefo.RestController;

import com.example.filrougefo.entity.Category;
import com.example.filrougefo.entity.Product;
import com.example.filrougefo.service.category.IntCategoryService;
import com.example.filrougefo.service.product.IntProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private final IntCategoryService categoryService;
    @GetMapping("/{id}")
    public Category fetchAllCategorys(@PathVariable int id){
        Category category = categoryService.findById(id);
        return category;

    }

}
