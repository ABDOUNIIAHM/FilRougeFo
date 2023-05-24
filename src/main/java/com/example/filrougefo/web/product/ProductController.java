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
    private OrderMapper orderMapper;
    private ProductMapper productMapper;
    private IntMonthService monthService;
    private IntCategoryService categoryService;

    @GetMapping
    public String listProduct(Model model) {
        List<Product> productList = productService.findAll();

        //TODO: Category + month DTO
        List<Month> monthList = monthService.findAll();
        List<Category> categoryList = categoryService.findAll();

        List<ProductDTO> productDTOsList = productList
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());

        model.addAttribute("productList", productDTOsList);
        model.addAttribute("monthList", monthList);
        model.addAttribute("categoryList", categoryList);


        return "product/product-list";
    }

    @GetMapping("/details/{id}")
    public String detailProduct(Model model, @PathVariable int id) {
        Product product = productService.findById(id);
        ProductDTO productDTO = productMapper.toDTO(product);

        List<Month> monthList = monthService.findAll();

        model.addAttribute("product", productDTO);
        model.addAttribute("monthList", monthList);

        return "product/product-detail";
    }

    @GetMapping("/month/{monthName}")
    public String listProductPerMonth(
            @PathVariable String monthName,
            Model model) {

        //TODO: Category + month DTO
        List<Month> monthList = monthService.findAll();
        List<Category> categoryList = categoryService.findAll();

        List<Product> productList = productService.findAllProductPerMonth(monthName);

        List<ProductDTO> productDTOsList = productList
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());

        model.addAttribute("productList", productDTOsList);
        model.addAttribute("monthList", monthList);
        model.addAttribute("categoryList", categoryList);

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

}
