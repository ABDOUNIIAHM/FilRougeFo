package com.example.filrougefo.web.product;

import com.example.filrougefo.entity.*;
import com.example.filrougefo.exception.MonthNotFoundException;
import com.example.filrougefo.security.ClientAuthDetail;
import com.example.filrougefo.service.category.IntCategoryService;
import com.example.filrougefo.service.month.IntMonthService;
import com.example.filrougefo.exception.ProductNotFoundException;
import com.example.filrougefo.service.order.IntOrderService;
import com.example.filrougefo.service.order.OrderService;
import com.example.filrougefo.service.product.IntProductService;
import com.example.filrougefo.web.category.CategoryDto;
import com.example.filrougefo.web.category.CategoryMapper;
import com.example.filrougefo.web.order.OrderMapper;

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

        List<ProductDTO> productDTOsList = productList
                .stream()
                .map(productMapper::toDTO)
                .toList();

        model.addAttribute("productList", productDTOsList);
        model.addAttribute("monthList", monthList);
        model.addAttribute("categoryList", categoryDtoList);


        return "product/product-list";
    }

    @GetMapping("/details/{id}")
    public String detailProduct(Model model, @PathVariable int id) {

        Product product = productService.findById(id);
        ProductDTO productDTO = productMapper.toDTO(product);

        List<Month> monthList = monthService.findAll();
        List<MonthDTO> monthDTOList = monthList
                .stream()
                .map(monthMapper::toDTO)
                .toList();

        model.addAttribute("product", productDTO);
        model.addAttribute("monthList", monthDTOList);

        return "product/product-detail";
    }

    @GetMapping("/month/{monthName}")
    public String listProductPerMonth(@PathVariable String monthName, Model model) {

        List<Product> productList = productService.findAllProductPerMonth(monthName);
        List<Month> monthList = monthService.findAll();
        List<Category> categoryList = categoryService.findAll();

        List<ProductDTO> productDTOsList = productList
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());

        List<CategoryDto> categoryDtoList = categoryList
                .stream()
                .map(categoryMapper::toDTO)
                .toList();

        List<MonthDTO> monthDTOList = monthList
                .stream()
                .map(monthMapper::toDTO)
                .toList();

        model.addAttribute("productList", productDTOsList);
        model.addAttribute("monthList", monthDTOList);
        model.addAttribute("categoryList", categoryDtoList);

        return "product/product-list";
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

    @PostMapping
    public String searchProduct(@RequestParam("keyword") String keyword, Model model) {
        List<Product> productList = productService.searchByKeyword(keyword);

        List<ProductDTO> productDTOsList = productList
                .stream()
                .map(productMapper::toDTO)
                .toList();


        List<Category> categoryList = categoryService.searchByKeyword(keyword);
        List<Month> monthList = monthService.findAll();

        List<MonthDTO> monthDTOList = monthList
                .stream()
                .map(monthMapper::toDTO)
                .toList();

        List<CategoryDto> categoryDtoList = categoryList
                .stream()
                .map(categoryMapper::toDTO)
                .toList();

        model.addAttribute("productList", productDTOsList);
        model.addAttribute("monthList", monthList);
        model.addAttribute("categoryList", categoryDtoList);
        model.addAttribute("keyword", keyword);

        return "product/product-list";
    }


}
