package com.example.filrougefo.web.category;

import com.example.filrougefo.entity.Category;
import com.example.filrougefo.exception.CategoryNotFoundException;
import com.example.filrougefo.service.category.IntCategoryService;
import com.example.filrougefo.web.Product.ProductDTO;
import com.example.filrougefo.web.Product.ProductMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private IntCategoryService categoryService;
    private CategoryMapper categoryMapper;
    private ProductMapper productMapper;

    @GetMapping
    public String getAllCategories(Model model){

        model.addAttribute("categories", mapCategoryListToDto());
        return "product-and-category";
    }

    @GetMapping("/{id}")
    public String getCategoryProducts(@PathVariable int id, Model model){

        model.addAttribute("products",mapCategoryProductsToDto(id));
        model.addAttribute("categories", mapCategoryListToDto());
        return "product-and-category";
    }
    private List<CategoryDto> mapCategoryListToDto(){

        List<Category> categoryList = categoryService.findAll();
        return categoryList
                .stream()
                .map(c -> categoryMapper.toDTO(c))
                .collect(Collectors.toList());
    }
    private List<ProductDTO> mapCategoryProductsToDto(int id){

        return categoryService
                .findById(id)
                .getProducts()
                .stream()
                .map(p -> productMapper.toDTO(p))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(value = {CategoryNotFoundException.class})
    public ModelAndView handleError(HttpServletRequest req, CategoryNotFoundException ex) {

        //logger.error("Request: " + req.getRequestURL() + " raised " + ex);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        return mav;
    }
}
