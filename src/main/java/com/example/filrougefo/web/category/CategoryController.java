package com.example.filrougefo.web.category;

import com.example.filrougefo.entity.Category;
import com.example.filrougefo.exception.CategoryNotFoundException;
import com.example.filrougefo.service.category.IntCategoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private IntCategoryService categoryService;
    private CategoryMapper categoryMapper;
    @GetMapping("/{id}")
    public String getAllProducts(@PathVariable int id, Model model){

        CategoryDto cat = categoryMapper
                .toDTO(categoryService.findById(id));

        model.addAttribute("category", cat);
        model.addAttribute("products",cat.getProductList());
        return "list-category";
    }
    @GetMapping
    public String getAllCategories(Model model){

        model.addAttribute("categories", mapCategoryListToDto());
        return "list-category";
    }




    private List<CategoryDto> mapCategoryListToDto(){

        List<Category> categoryList = categoryService.findAll();
        return categoryList
                .stream()
                .map(c -> categoryMapper.toDTO(c))
                .collect(Collectors.toList());
    }


    @ExceptionHandler(value = {CategoryNotFoundException.class})
    public String handleError(HttpServletRequest req, CategoryNotFoundException ex) {


        //logger.error("Request: " + req.getRequestURL() + " raised " + ex);

        //ModelAndView mav = new ModelAndView();
        //mav.addObject("exception", ex);
        //mav.addObject("url", req.getRequestURL());
        //mav.setViewName("error");
        return "error";
    }
}
