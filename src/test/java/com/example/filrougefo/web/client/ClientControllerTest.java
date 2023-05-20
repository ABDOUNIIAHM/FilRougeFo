package com.example.filrougefo.web.client;

import com.example.filrougefo.service.client.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.*;

@Import(ClientControllerTestConfiguration.class)
@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClientService clientService;
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private PhoneNumberMapper phoneNumberMapper;

    @Test
    void ShouldReturnFormToRegister() throws Exception {
        //given
        ClientDto clientDto = new ClientDto();
        clientDto.getAddressList().add(new AddressDto());
        clientDto.getPhoneNumberList().add(new PhoneNumberDto());
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/client/register"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("signup-form"))
                .andExpect(MockMvcResultMatchers.model().attribute("clientDto", equalToObject(clientDto)));
    }

}