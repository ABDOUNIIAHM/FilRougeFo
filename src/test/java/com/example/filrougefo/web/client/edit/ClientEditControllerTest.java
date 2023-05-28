package com.example.filrougefo.web.client.edit;

import com.example.filrougefo.security.ClientAuthDetail;
import com.example.filrougefo.security.ClientDetailServiceImpl;
import com.example.filrougefo.security.WebSecurityConfig;
import com.example.filrougefo.service.client.ClientService;
import com.example.filrougefo.service.order.OrderService;
import com.example.filrougefo.web.client.AddressMapper;
import com.example.filrougefo.web.client.ClientConfig;
import com.example.filrougefo.web.client.ClientMapper;
import com.example.filrougefo.web.client.PhoneNumberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@Import({ClientConfig.class, WebSecurityConfig.class})
@WebMvcTest(ClientEditController.class)
class ClientEditControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClientService clientService;
    @MockBean
    private OrderService orderService;
    @MockBean
    private ClientAuthDetail clientAuthDetail;
    @MockBean
    private ClientDetailServiceImpl clientDetailService;
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private PhoneNumberMapper phoneNumberMapper;

}