package com.example.filrougefo.web.client;

import com.example.filrougefo.service.client.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.FlashMap;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

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
    @Mock
    BindingResult bindingResult;

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
    @Test
    void ShouldReturnFormPageWithErrors() throws Exception {

        //given
        ClientDto clientDto = new ClientDto();
        clientDto.getAddressList().add(new AddressDto());
        clientDto.getPhoneNumberList().add(new PhoneNumberDto());
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/client/register")
                        .flashAttr("bindingResult", bindingResult)
                        .flashAttr("clientDto", clientDto))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeHasErrors("bindingResult"))
                .andExpect(MockMvcResultMatchers.model().attribute("clientDto", equalToObject(clientDto)))
                .andExpect(MockMvcResultMatchers.view().name("signup-form"));
        //then


    }
    @Test
    void ShouldReturnSuccessPageWhenRegistered() throws Exception {
        //given

        //when
        //then

    }
    @Test
    void ShouldHandleExceptionAndReturnItsMessageToView() throws Exception {
        //given

        //when
        //then

    }

}