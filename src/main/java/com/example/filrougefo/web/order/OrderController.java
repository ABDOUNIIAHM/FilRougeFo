package com.example.filrougefo.web.order;

import com.example.filrougefo.service.order.IntOrderService;
import com.example.filrougefo.service.orderline.IntOrderLineService;
import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class OrderController {
    private IntOrderService orderService;
    private IntOrderLineService orderLineService;

    @GetMapping("cart")
    public String getShoppingCart(Model model){

        return "shopping-cart";
    }

    @PostMapping
    public String addProductToCart(@PathParam("quantity") int quantity){
        return null;
    }
}
