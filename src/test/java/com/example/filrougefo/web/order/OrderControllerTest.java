package com.example.filrougefo.web.order;

import com.example.filrougefo.entity.*;
import com.example.filrougefo.security.ClientAuthDetail;
import com.example.filrougefo.service.month.IntMonthService;
import com.example.filrougefo.service.order.IntOrderService;
import com.example.filrougefo.service.order.OrderService;
import com.example.filrougefo.service.orderline.OrderLineService;
import com.example.filrougefo.service.product.IntProductService;
import com.example.filrougefo.web.order.paymentDto.CardPaymentDto;
import com.example.filrougefo.web.product.ProductMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@Import(OrderConfig.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IntOrderService orderService;
    @MockBean
    private ClientAuthDetail clientAuthDetail;
    @MockBean
    private IntProductService productService;
    @MockBean
    private OrderLineService orderLineService;
    @MockBean
    private IntMonthService monthService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderLineMapper orderLineMapper;
    @Autowired
    private OrderMapper orderMapper;

    @WithMockUser
    @Test
    void ShouldReturnAllOrdersView() throws Exception {
        //given
        OrderLine orderLine = new OrderLine(); orderLine.setQuantity(BigDecimal.ONE);
        Order order = new Order(); order.getOrderLines().add(orderLine);
        List<Order> orders = List.of(new Order());
        //when
        when(clientAuthDetail.getClient()).thenReturn(new Client());
        when(orderService.hasPendingOrder(any(Client.class))).thenReturn(order);
        when(orderService.getNonPendingOrders(ArgumentMatchers.any(Client.class))).thenReturn(orders);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/orders"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("orders",getDtosFromListOrder(orders)))
                .andExpect(MockMvcResultMatchers.view().name("order/order-history"));
    }

    @WithMockUser
    @Test
    void ShouldReturnOrderDetailView() throws Exception {
        //given
        Order order = new Order();
        Product product = new Product();product.setPricePerUnit(BigDecimal.TEN);
        OrderLine orderLine = new OrderLine();orderLine.setQuantity(BigDecimal.ZERO);orderLine.setDiscount(BigDecimal.valueOf(0));
        orderLine.setOrder(order);orderLine.setProduct(product);orderLine.setQuantity(BigDecimal.ONE);
        order.getOrderLines().add(orderLine);

        List<OrderLine> orderLines = List.of(orderLine);
        //when
        when(clientAuthDetail.getClient()).thenReturn(new Client());
        when(orderLineService.findAllOrderLinesByOrderId(ArgumentMatchers.any(long.class))).thenReturn(orderLines);
        when(orderService.hasPendingOrder(any(Client.class))).thenReturn(order);
        when(orderService.findOrderById(any(long.class))).thenReturn(order);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/orders/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("orderLines",getDtosFromListOrderLine(orderLines)))
                .andExpect(MockMvcResultMatchers.view().name("order-detail"));

    }
    @WithMockUser
    @Test
    void ShouldReturnCartView() throws Exception {
        //given
        Order order = new Order();
        OrderLine orderLine = new OrderLine();
        Product product = new Product();product.setPricePerUnit(BigDecimal.TEN);
        orderLine.setProduct(product);orderLine.setQuantity(BigDecimal.ZERO);orderLine.setDiscount(BigDecimal.valueOf(0));
        order.getOrderLines().add(orderLine);
        //orderLine.setOrder(order);
        //when
        when(clientAuthDetail.getClient()).thenReturn(new Client());

        when(orderService.hasPendingOrder(any(Client.class))).thenReturn(order);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/cart"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("pendingOrderDto",orderMapper.toDTO(order)))
                .andExpect(MockMvcResultMatchers.view().name("order/cart"));
    }
    @WithMockUser
    @Test
    void ShouldRedirectToProductDetailViewWhenAddingProduct() throws Exception {
        //given
        Order order = new Order();
        OrderLine orderLine = new OrderLine();
        Product product = new Product();product.setStock(BigDecimal.valueOf(1));product.setId(1);
        orderLine.setProduct(product);
        orderLine.setQuantity(BigDecimal.valueOf(1));
        orderLine.setOrder(new Order());

        //when
        when(productService.findById(any(int.class))).thenReturn(product);
        when(clientAuthDetail.getClient()).thenReturn(new Client());
        when(orderService.hasPendingOrder(any(Client.class))).thenReturn(order);
        when(orderService.addProductToOrder(any(int.class),any(long.class),any(Client.class))).thenReturn(orderLine);
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/add-to-cart/1")
                        .with(csrf())
                        .param("quantity","1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/products/details/1"));
    }

    @WithMockUser
    @Test
    void ShouldReturnProductDetailViewWithErrorWhenAddingProduct() throws Exception {
        //given
        Category category = new Category();category.setName("");
        Order order = new Order();

        Product product = new Product();product.setStock(BigDecimal.valueOf(1));product.setId(1);product.setName("");
        product.setCategory(category);product.setVat(BigDecimal.valueOf(1));

        OrderLine orderLine = new OrderLine();orderLine.setProduct(product);
        orderLine.setQuantity(BigDecimal.valueOf(1));orderLine.setOrder(order);

        //when
        when(productService.findById(any(int.class))).thenReturn(product);
        when(clientAuthDetail.getClient()).thenReturn(new Client());
        when(orderService.hasPendingOrder(any(Client.class))).thenReturn(order);
        when(orderService.addProductToOrder(any(int.class),any(long.class),any(Client.class))).thenReturn(orderLine);
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/add-to-cart/1")
                        .with(csrf())
                        .param("quantity","2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("error","La quantité demandée est supérieure au stock disponible."))
                .andExpect(MockMvcResultMatchers.view().name("product/product-detail"));

    }
    @WithMockUser
    @Test
    void ShouldRedirectToCartView() throws Exception {
        //given
        //when
        when(orderLineService.deleteOrderLine(any(long.class))).thenReturn(true);
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/cart/delete/1")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/auth/cart"));
    }
    @WithMockUser
    @Test
    void ShouldReturnErrorViewWhenDeleteDosentWork() throws Exception {
        //given
        //when
        when(orderLineService.deleteOrderLine(any(long.class))).thenReturn(false);
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/cart/delete/1")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("error"));
    }
    @WithMockUser
    @Test
    void ShouldReturnPaymentView() throws Exception {
        //given
        Order order = new Order();
        CardPaymentDto paymentDto = new CardPaymentDto();
        paymentDto.setId(1);
        //when
        when(clientAuthDetail.getClient()).thenReturn(new Client());
        when(orderService.hasPendingOrder(any(Client.class))).thenReturn(order);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/payment").param("idOrder","1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("paymentDto",paymentDto))
                .andExpect(MockMvcResultMatchers.view().name("payment"));
    }
    @WithMockUser
    @Test
        void ShouldReturnSuccessOrderView() throws Exception {
        //given
        CardPaymentDto paymentDto = new CardPaymentDto();
        paymentDto.setId(1);
        paymentDto.setCvv("500");paymentDto.setCardHolder("joe");
        paymentDto.setCardNumber("5000500050005000");paymentDto.setExpirationDate(LocalDate.of(2025,5,6));
        //when
        when(orderService.validateOrder(any(long.class))).thenReturn(true);
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/payment/1").with(csrf())
                        .flashAttr("paymentDto",paymentDto))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.model().attribute("paymentDto",paymentDto))
                .andExpect(MockMvcResultMatchers.view().name("success-order"));
    }
    @WithMockUser
    @Test
    void ShouldReturnPaymentViewWhenFormHasErrors() throws Exception {
        //given
        CardPaymentDto paymentDto = new CardPaymentDto();
        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/payment/1").with(csrf())
                        .flashAttr("paymentDto",paymentDto))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("paymentDto",paymentDto))
                .andExpect(MockMvcResultMatchers.view().name("payment"));
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
                .map(x -> orderLineMapper.toDTO(x))
                .collect(Collectors.toList());
    }
}