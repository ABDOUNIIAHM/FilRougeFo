package com.example.filrougefo.RestController;

import com.example.filrougefo.entity.Category;
import com.example.filrougefo.service.category.IntCategoryService;
import com.example.filrougefo.web.category.CategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryRestController {
    private final IntCategoryService categoryService;
    private CategoryMapper categoryProductMapper;


}
