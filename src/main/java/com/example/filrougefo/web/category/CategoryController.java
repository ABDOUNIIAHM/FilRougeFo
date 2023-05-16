package com.example.filrougefo.web.category;

import com.example.filrougefo.entity.Category;
import com.example.filrougefo.exception.CategoryControllerException;
import com.example.filrougefo.service.category.IntCategoryService;
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
    private CategoryProductMapper categoryProductMapper;
    @GetMapping("/{id}")
    public String getAllProducts(@PathVariable int id, Model model){

        CategoryProductDto cat = categoryProductMapper
                .toDTO(categoryService.findById(id));

        model.addAttribute("category", cat);
        model.addAttribute("products",cat.getProductList());
        return "list-category";
    }


    @ExceptionHandler(value = {CategoryControllerException.class})
    public String handleError(HttpServletRequest req, CategoryControllerException ex) {
        System.out.println("handle error activated");

        //logger.error("Request: " + req.getRequestURL() + " raised " + ex);

        //ModelAndView mav = new ModelAndView();
        //mav.addObject("exception", ex);
        //mav.addObject("url", req.getRequestURL());
        //mav.setViewName("error");
        return "error";
    }
}
