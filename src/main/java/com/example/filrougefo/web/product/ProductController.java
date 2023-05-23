package com.example.filrougefo.web.product;

import com.example.filrougefo.entity.Month;
import com.example.filrougefo.entity.Product;
import com.example.filrougefo.service.month.IntMonthService;
import com.example.filrougefo.exception.ProductNotFoundException;
import com.example.filrougefo.service.order.IntOrderService;
import com.example.filrougefo.service.product.IntProductService;
import com.example.filrougefo.web.order.OrderMapper;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private IntProductService productService;
    private IntOrderService orderService;
    private OrderMapper orderMapper;
    private ProductMapper productMapper;
    private IntMonthService monthService;

    @GetMapping
    public String listProduct(Model model) {
        List<Product> productList = productService.findAll();

        List<ProductDTO> productDTOsList = productList
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());

        model.addAttribute("productList", productDTOsList);

        return "product/product-list";
    }

    @GetMapping("/detail{id}")
    public String detailProduct(Model model, @RequestParam int id) {
        Product product = productService.findById(id);
        ProductDTO productDTO = productMapper.toDTO(product);

        List<Month> monthList = monthService.findAll();

        model.addAttribute("product", productDTO);
        model.addAttribute("monthList", monthList);

        return "product/product-detail";
    }

    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ModelAndView handleError(HttpServletRequest req, ProductNotFoundException ex) {

        //logger.error("Request: " + req.getRequestURL() + " raised " + ex);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        return mav;
    }

}
