package com.example.filrougefo.web.order;

import com.example.filrougefo.entity.Month;
import com.example.filrougefo.entity.Order;
import com.example.filrougefo.entity.OrderLine;
import com.example.filrougefo.entity.Product;
import com.example.filrougefo.security.ClientAuthDetail;
import com.example.filrougefo.service.month.IntMonthService;
import com.example.filrougefo.service.order.IntOrderService;
import com.example.filrougefo.service.orderline.IntOrderLineService;
import com.example.filrougefo.service.product.IntProductService;
import com.example.filrougefo.web.order.paymentDto.CardPaymentDto;
import com.example.filrougefo.web.product.MonthDTO;
import com.example.filrougefo.web.product.MonthMapper;
import com.example.filrougefo.web.product.ProductDTO;
import com.example.filrougefo.web.product.ProductMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class OrderController {
    private final IntProductService productService;
    private final IntOrderService orderService;
    private ProductMapper productMapper;
    private final OrderMapper orderMapper;
    private ClientAuthDetail authenticatedClient;
    private final OrderLineMapper orderLineMapper;
    private final IntOrderLineService orderLineService;
    private IntMonthService monthService;
    private MonthMapper monthMapper;



    private boolean handleOutOfStockProducts(Order pendingOrder, Model model) {
        List<Product> outOfStock = productService.checkIfAvailableStock(pendingOrder);

        if (outOfStock.size() > 0) {
            List<String> outOfStockProducts = outOfStock.stream()
                    .map(p -> p.getName() + " (Stock restant : " + p.getStock() + " " + p.getUnit() + ")")
                    .toList();

            OrderDto pendingOrderDto = orderMapper.toDTO(pendingOrder);
            model.addAttribute("pendingOrderDto", pendingOrderDto);
            model.addAttribute("outOfStockProducts", outOfStockProducts);
            return true;
        }

        return false;
    }

    @GetMapping("/orders")
    public String getAllOrders(Model model) {

        List<OrderDto> allOrders = getDtosFromListOrder(orderService.getNonPendingOrders(authenticatedClient.getClient()));

        model.addAttribute("status","PENDING");
        model.addAttribute("orders",allOrders);

        return "order/order-history";
    }


    @GetMapping("/orders/{id}")
    public String getOrderDetails(@PathVariable int id, Model model) {

        List<OrderLineDto> orderLines =
                getDtosFromListOrderLine(orderLineService.findAllOrderLinesByOrderId(id));

        model.addAttribute("orderLines", orderLines);
        model.addAttribute("order",orderMapper.toDTO(orderService.findOrderById(id)));

        return "order/order-detail";
    }
    @GetMapping("/cart")
    public String getMyCart(Model model){

        Order pendingOrder = orderService.hasPendingOrder(authenticatedClient.getClient());
        OrderDto pendingOrderDto = orderMapper.toDTO(pendingOrder);
        model.addAttribute("pendingOrderDto", pendingOrderDto);
        return "order/cart";
    }

    @PostMapping("/add-to-cart/{id}")
    public String addProductToCart(@RequestParam("quantity") String quantity, Model model, @PathVariable int id) {

        Product product = productService.findById(id);
        ProductDTO productDTO = productMapper.toDTO(product);
        double qty = Double.parseDouble(quantity);

        if (orderService.addProductToOrder(id, qty, authenticatedClient.getClient()) == true) {
            // La quantité est suffisante
            return "redirect:/products";
        } else {
            // La quantité est insuffisante, renvoyez un message d'erreur
            String error = "La quantité demandée est supérieure au stock disponible.";
            List<Month> monthList = monthService.findAll();

            List<MonthDTO> monthDTOList = monthList
                    .stream()
                    .map(monthMapper::toDTO)
                    .toList();
            model.addAttribute("monthList", monthDTOList);
            model.addAttribute("product",productDTO);
            model.addAttribute("error", error);
            return "product/product-detail";
        }
    }
    @PostMapping("/cart/delete/{idOrderLine}")
    public String deleteOrderLine(@PathVariable long idOrderLine){

        if(orderLineService.deleteOrderLine(idOrderLine)==true){
            return "redirect:/auth/cart";
        }
        return "error";
    }

    @GetMapping("/payment")
    public String getPaymentForm(Model model, @RequestParam("idOrder") long idOrder){
        Order pendingOrder = orderService.hasPendingOrder(authenticatedClient.getClient());
        boolean isOutOfStock = handleOutOfStockProducts(pendingOrder, model);

        if (isOutOfStock) {
            return "order/cart";
        }
        CardPaymentDto paymentDto = new CardPaymentDto();
        paymentDto.setId(idOrder);
        model.addAttribute("paymentDto", paymentDto);
        return "payment";
    }

    @PostMapping("/payment/{id}")
    public String confirmPayment(@ModelAttribute("paymentDto") @Valid CardPaymentDto paymentDto, BindingResult bindingResult, @PathVariable long id, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("paymentDto", paymentDto);
            return "payment";
        }

        Order pendingOrder = orderService.hasPendingOrder(authenticatedClient.getClient());
        boolean isOutOfStock = handleOutOfStockProducts(pendingOrder, model);

        if (isOutOfStock) {
            return "order/cart";
        }

        productService.updateProductStock(pendingOrder);
        orderService.validateOrder(id);
        return "success-order";
    }

    private List<OrderDto> getDtosFromListOrder(List<Order> orders){
        List<OrderDto> dtos = orders
                .stream()
                .map(x -> orderMapper.toDTO(x))
                .collect(Collectors.toList());

        return dtos;
    }
    private List<OrderLineDto> getDtosFromListOrderLine(List<OrderLine> orderLines){

        return orderLines
                .stream()
                .map(orderLineMapper::toDTO)
                .collect(Collectors.toList());
    }

}
