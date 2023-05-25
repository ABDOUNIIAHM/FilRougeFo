package com.example.filrougefo.web;

import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.Order;
import com.example.filrougefo.security.ClientAuthDetail;
import com.example.filrougefo.service.order.IntOrderService;
import com.zaxxer.hikari.util.FastList;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@AllArgsConstructor
public class NavBarController {
    private ClientAuthDetail authenticatedClient;
    private IntOrderService orderService;

    public int countCartItems(Client client) {
        Order pendingOrder = orderService.hasPendingOrder(client);
        return pendingOrder.getOrderLines().size();
    }
    @ModelAttribute
    public void commonAttributes(Model model, HttpServletRequest req) {

        String requestURI = req.getRequestURI();
        if (requestURI.contains("/auth/cart/delete")) {
            return;
        }
        if (authenticatedClient.getClient() != null) {
            int cartCount = countCartItems(authenticatedClient.getClient());
            model.addAttribute("cartCount", cartCount);
        }
    }
}
