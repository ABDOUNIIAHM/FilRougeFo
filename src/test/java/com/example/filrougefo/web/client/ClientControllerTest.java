package com.example.filrougefo.web.client;

import com.example.filrougefo.exception.ClientAlreadyExistException;
import com.example.filrougefo.security.ClientAuthDetail;
import com.example.filrougefo.security.ClientDetailServiceImpl;
import com.example.filrougefo.security.WebSecurityConfig;
import com.example.filrougefo.service.client.ClientService;
import com.example.filrougefo.service.order.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


@Import({ClientConfig.class, WebSecurityConfig.class})
@WebMvcTest(ClientController.class)
class ClientControllerTest {

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
                        .flashAttr("clientDto", clientDto).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("clientDto", equalToObject(clientDto)))
                .andExpect(MockMvcResultMatchers.view().name("signup-form"));
        //then
    }
    @Test
    void ShouldReturnSuccessPageWhenRegistered() throws Exception {
        //given
        List<AddressDto> addresses = List.of(new AddressDto(0,"","18","bis","rue de toto","","44000","nantes"));
        List<PhoneNumberDto> phones = List.of(new PhoneNumberDto(0,"0606060606"));
        ClientDto clientDto = new ClientDto(0,"toto","toto","toto@tototo.com","toto","",null,addresses,phones);
        clientDto.setMatchingPassword("toto");
        //when
        when(clientService.isValidEmail(ArgumentMatchers.any(String.class))).thenReturn(true);
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/client/register")
                        .flashAttr("clientDto", clientDto).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("clientDto", equalToObject(clientDto)))
                .andExpect(MockMvcResultMatchers.view().name("success-signup"));

    }
    @Test
    void ShouldHandleExceptionAndReturnItsMessageToView() throws Exception {
        //given
        List<AddressDto> addresses = List.of(new AddressDto(0,"","18","bis","rue de toto","","44000","nantes"));
        List<PhoneNumberDto> phones = List.of(new PhoneNumberDto(0,"0606060606"));
        ClientDto clientDto = new ClientDto(0,"toto","toto","toto@toto.com","toto","",null,addresses,phones);
        clientDto.setMatchingPassword("toto");

        //when
        when(clientService.isValidEmail(ArgumentMatchers.any(String.class))).thenThrow( new ClientAlreadyExistException("message"));
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/client/register")
                        .flashAttr("clientDto", clientDto).with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("clientDto", equalToObject(clientDto)))
                .andExpect(MockMvcResultMatchers.view().name("signup-form"));
    }

}