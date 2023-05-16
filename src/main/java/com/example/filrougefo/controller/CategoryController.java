package com.example.filrougefo.controller;

import com.example.filrougefo.entity.Category;
import com.example.filrougefo.service.category.IntCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    private IntCategoryService categoryService;

    @GetMapping
    public String getAllCategories(Model model){

        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        return "list-category";
    }


}
