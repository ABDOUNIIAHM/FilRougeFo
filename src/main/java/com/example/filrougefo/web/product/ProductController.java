package com.example.filrougefo.web.product;

import com.example.filrougefo.entity.*;
import com.example.filrougefo.exception.CategoryNotFoundException;
import com.example.filrougefo.exception.MonthNotFoundException;
import com.example.filrougefo.service.category.IntCategoryService;
import com.example.filrougefo.service.month.IntMonthService;
import com.example.filrougefo.exception.ProductNotFoundException;
import com.example.filrougefo.service.product.IntProductService;
import com.example.filrougefo.web.category.CategoryDto;
import com.example.filrougefo.web.category.CategoryMapper;

import jakarta.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private IntProductService productService;
    private ProductMapper productMapper;
    private IntMonthService monthService;
    private IntCategoryService categoryService;
    private CategoryMapper categoryMapper;
    private MonthMapper monthMapper;

    @GetMapping
    public String listProduct(Model model) {

        List<Product> productList = productService.findAll();

        populateModelWithLists(model);
        List<ProductDTO> productDTOsList = productList
                .stream()
                .map(productMapper::toDTO)
                .toList();

        model.addAttribute("productList", productDTOsList);
    return "product/product-list";
    }

    @GetMapping("/details/{id}")
    public String detailProduct(Model model, @PathVariable int id) {

        Product product = productService.findById(id);
        ProductDTO productDTO = productMapper.toDTO(product);

        populateModelWithLists(model);

        model.addAttribute("product", productDTO);

        return "product/product-detail";
    }

    @GetMapping("/month/{id}")
    public String listProductPerMonth(@PathVariable int id, Model model) {

        List<Product> productList = productService.findAllProductPerMonth(id);

        List<ProductDTO> productDTOsList = productList
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());

        populateModelWithLists(model);

        model.addAttribute("productList", productDTOsList);

        return "product/product-list";
    }

    @GetMapping("/categories/{id}")
    public String getCategoryProducts(@PathVariable int id, Model model) {

        populateModelWithLists(model);

        model.addAttribute("productList",mapCategoryProductsToDto(id));
        return "product/product-list";
    }

    @PostMapping
    public String searchProduct(@RequestParam("keyword") String keyword, Model model) {

        List<Product> productList = productService.searchProductsGlobal(keyword);

        List<ProductDTO> productDTOsList = productList
                .stream()
                .map(productMapper::toDTO)
                .toList();

        populateModelWithLists(model);
        model.addAttribute("productList", productDTOsList);
        model.addAttribute("keyword", keyword);

        return "product/product-list";

    }

    // Populate the model with all months and all categories
    private void populateModelWithLists(Model model) {

        List<Category> categoryList = categoryService.findAll();
        List<Month> monthList = monthService.findAll();

        List<MonthDTO> monthDTOList = monthList
                .stream()
                .map(monthMapper::toDTO)
                .toList();

        List<CategoryDto> categoryDtoList = categoryList
                .stream()
                .map(categoryMapper::toDTO)
                .toList();

        model.addAttribute("categoryList", categoryDtoList);
        model.addAttribute("monthList", monthDTOList);
    }

    private List<CategoryDto> mapCategoryListToDto() {
        List<Category> categoryList = categoryService.findAll();
        return categoryList
                .stream()
                .map(c -> categoryMapper.toDTO(c))
                .collect(Collectors.toList());
    }

    private List<ProductDTO> mapCategoryProductsToDto(int id) {
        return categoryService
                .findById(id)
                .getProducts()
                .stream()
                .map(p -> productMapper.toDTO(p))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(value = {ProductNotFoundException.class, MonthNotFoundException.class})
    public ModelAndView handleError(HttpServletRequest req, ProductNotFoundException ex) {

        //logger.error("Request: " + req.getRequestURL() + " raised " + ex);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        return mav;
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
