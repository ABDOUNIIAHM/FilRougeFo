package com.example.filrougefo.web.Product;

import com.example.filrougefo.entity.Product;
import com.example.filrougefo.exception.CategoryNotFoundException;
import com.example.filrougefo.exception.ProductNotFoundException;
import com.example.filrougefo.service.product.IntProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/products")
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

    //pour tester la partie commande !
    @GetMapping("/{id}")
    public String detailProduct(Model model, @PathVariable int id){

        ProductDTO productDto =productMapper.toDTO(productService.findById(id));
        model.addAttribute("productDto",productDto);

        return "detail-product";

    }
    //pour tester la partie commande !



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
