package com.example.filrougefo.web.order;

import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.Order;
import com.example.filrougefo.entity.OrderLine;
import com.example.filrougefo.entity.Product;
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
import static org.mockito.Mockito.when;
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
        when(orderService.getNonPendingOrders(ArgumentMatchers.any(Client.class))).thenReturn(orders);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/orders"))
                .andExpect(MockMvcResultMatchers.status().isOk())
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
        List<OrderLine> orderLines = List.of(new OrderLine(), new OrderLine());
        Order order = new Order();
        order.setId(1);
        //when
        when(orderLineService.findAllOrderLinesByOrderId(ArgumentMatchers.any(long.class))).thenReturn(orderLines);
        when(orderService.hasPendingOrder(any(Client.class))).thenReturn(order);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/cart"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("order-detail"));

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