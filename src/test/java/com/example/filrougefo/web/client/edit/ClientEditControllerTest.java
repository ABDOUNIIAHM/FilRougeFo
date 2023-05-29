package com.example.filrougefo.web.client.edit;

import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.Order;
import com.example.filrougefo.entity.OrderLine;
import com.example.filrougefo.security.ClientAuthDetail;
import com.example.filrougefo.security.ClientDetailServiceImpl;
import com.example.filrougefo.security.WebSecurityConfig;
import com.example.filrougefo.service.address.IntAddressService;
import com.example.filrougefo.service.client.IntClientService;
import com.example.filrougefo.service.order.IntOrderService;
import com.example.filrougefo.service.orderline.IntOrderLineService;
import com.example.filrougefo.service.phonenumber.IntPhoneNumberService;
import com.example.filrougefo.web.client.AddressMapper;
import com.example.filrougefo.web.client.ClientMapper;
import com.example.filrougefo.web.client.PhoneNumberMapper;
import com.example.filrougefo.web.order.OrderMapper;

import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;



@Import({ClientEditConfig.class, WebSecurityConfig.class})
@WebMvcTest(ClientEditController.class)
class ClientEditControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IntClientService clientService;
    @MockBean
    private IntAddressService addressService;
    @Autowired
    private ClientProfileMapper clientProfileMapper;
    @MockBean
    private IntOrderService orderService;
    @MockBean
    private IntOrderLineService orderLineService;
    @MockBean
    private IntPhoneNumberService phoneNumberService;
    @MockBean
    private ClientAuthDetail clientAuthDetail;
    @MockBean
    private ClientDetailServiceImpl clientDetailService;
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private PhoneNumberMapper phoneNumberMapper;

    @Test
    @WithMockUser
    void ShouldReturnClientDetailsView() throws Exception {
        //given
        OrderLine orderLine = new OrderLine(); orderLine.setQuantity(BigDecimal.ONE);
        Order order = new Order(); order.getOrderLines().add(orderLine);
        List<Order> orders = List.of(new Order());
        Client client = new Client(); client.setOrderList(orders);
        //when
        when(clientAuthDetail.getClient()).thenReturn(client);
        when(orderService.hasPendingOrder(any(Client.class))).thenReturn(order);
        when(orderService.getNonPendingOrders(ArgumentMatchers.any(Client.class))).thenReturn(orders);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/client/detail"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("client/client-layout"));
    }

}