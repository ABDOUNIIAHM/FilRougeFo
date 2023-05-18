package com.example.filrougefo.web.Product;

import com.example.filrougefo.entity.Product;
import com.example.filrougefo.service.product.IntProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products/")
@AllArgsConstructor
public class ProductController {

    private IntProductService productService;
    private ProductMapper productMapper;

    @GetMapping
    public String listProducts(Model model) {
        List<Product> productList = productService.findAll();

        List<ProductDTO> productDTOsList = productList
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());

        model.addAttribute("productList", productDTOsList);

        return "product-list";
    }

}
