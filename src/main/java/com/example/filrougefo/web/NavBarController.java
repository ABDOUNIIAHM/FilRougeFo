package com.example.filrougefo.web;

import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.Order;
import com.example.filrougefo.security.ClientAuthDetail;
import com.example.filrougefo.service.order.IntOrderService;
import com.example.filrougefo.web.Admin.ApiController;
import com.example.filrougefo.web.client.ClientController;
import com.example.filrougefo.web.order.OrderController;
import com.example.filrougefo.web.product.ProductController;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(assignableTypes = {
        ProductController.class,
        OrderController.class,
        ClientController.class,
        ApiController.class})
@AllArgsConstructor
public class NavBarController {
    private ClientAuthDetail authenticatedClient;
    private IntOrderService orderService;

    private int countCartItems(Client client) {
        Order pendingOrder = orderService.hasPendingOrder(client);
        return pendingOrder.getOrderLines().size();
    }

    @ModelAttribute
    public void commonAttributes(Model model, HttpServletRequest req, Authentication authentication) {

        String requestURI = req.getRequestURI();
        if (requestURI.contains("/auth/cart/delete")) {
            return;
        }
        if(authentication != null && authentication.isAuthenticated()){
            if (authenticatedClient != null) {
                int cartCount = countCartItems(authenticatedClient.getClient());
                model.addAttribute("cartCount", cartCount);
            }
        }
    }
}


