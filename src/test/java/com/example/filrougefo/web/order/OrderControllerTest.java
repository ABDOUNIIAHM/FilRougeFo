package com.example.filrougefo.web.order;

import com.example.filrougefo.entity.*;
import com.example.filrougefo.security.ClientAuthDetail;
import com.example.filrougefo.service.order.OrderService;
import com.example.filrougefo.service.orderline.OrderLineService;
import com.example.filrougefo.web.client.AddressDto;
import com.example.filrougefo.web.client.ClientDto;
import com.example.filrougefo.web.client.PhoneNumberDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@Import(OrderConfig.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderService orderService;
    @MockBean
    private ClientAuthDetail clientAuthDetail;
    @MockBean
    private OrderLineService orderLineService;
    @Autowired
    private OrderLineMapper orderLineMapper;
    @Autowired
    private OrderMapper orderMapper;

    @WithMockUser
    @Test
    void ShouldReturnAllOrdersView() throws Exception {
        //given
        List<Order> orders = List.of(new Order(), new Order());
        //when
        when(clientAuthDetail.getClient()).thenReturn(new Client());
        when(orderService.getNonPendingOrders(ArgumentMatchers.any(Client.class))).thenReturn(orders);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/orders"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("orders",getDtosFromListOrder(orders)))
                .andExpect(MockMvcResultMatchers.view().name("All-Orders"));
    }
    @WithMockUser
    @Test
    void ShouldReturnOrderDetailView() throws Exception {

        //given
        Order order = new Order();
        Product product = new Product();
        OrderLine orderLine = new OrderLine();
        orderLine.setOrder(order);
        orderLine.setProduct(product);

        List<OrderLine> orderLines = List.of(orderLine);
        //when
        when(orderLineService.findAllOrderLinesByOrderId(ArgumentMatchers.any(long.class))).thenReturn(orderLines);
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
        Product product = new Product();
        orderLine.setProduct(product);
        order.getOrderLines().add(orderLine);
        //orderLine.setOrder(order);
        //when
        when(clientAuthDetail.getClient()).thenReturn(new Client());
        when(orderService.hasPendingOrder(any(Client.class))).thenReturn(order);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/cart"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("pendingOrderDto",orderMapper.toDTO(order)))
                .andExpect(MockMvcResultMatchers.view().name("cart"));
    }
    @WithMockUser
    @Test
    void ShouldRedirectToProductDetailView() throws Exception {
        //given
        OrderLine orderLine = new OrderLine();
        Product product = new Product();
        orderLine.setProduct(product);
        //when
        when(clientAuthDetail.getClient()).thenReturn(new Client());
        when(orderService.addProductToOrder(eq(1),eq(1),any(Client.class))).thenReturn(orderLine);
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/add-to-cart/1")
                        .with(csrf())
                        .param("quantity", "1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/products/1"))
                .andExpect(MockMvcResultMatchers.model().attribute("orderLine",orderLineMapper.toDTO(orderLine)));
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