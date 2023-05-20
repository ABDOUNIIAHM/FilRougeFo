package com.example.filrougefo.web.client;


import com.example.filrougefo.entity.Address;
import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.PhoneNumber;
import com.example.filrougefo.exception.ClientAlreadyExistException;
import com.example.filrougefo.service.client.IntClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import java.util.stream.Collectors;



@Controller
@AllArgsConstructor
public class ClientController {
    private IntClientService clientService;
    private ClientMapper clientMapper;
    private AddressMapper addressMapper;
    private PhoneNumberMapper phoneNumberMapper;

    @GetMapping("client/register")
    public String getRegisterForm(Model model){

        ClientDto clientDto = new ClientDto();
        System.out.println(clientDto.getFirstName());
        clientDto.getAddressList().add(new AddressDto());
        clientDto.getPhoneNumberList().add(new PhoneNumberDto());

        model.addAttribute("clientDto",clientDto);
        return "signup-form";
    }
    @PostMapping("client/register")
    public String addNewClient(@ModelAttribute("clientDto") @Valid ClientDto clientDto,
                               BindingResult bindingResult,
                               Model model){

        if(bindingResult.hasErrors()){
            return "signup-form";
        }

        try {
            Client client = getClientFromClientDto(clientDto);
            clientService.isValidEmail(clientDto.getEmail());
            clientService.createClient(client);
            return "success-signup";

        } catch (ClientAlreadyExistException ex){
            return handleClientRegistrationError(ex, clientDto, model);
        }
    }

    private String handleClientRegistrationError(ClientAlreadyExistException ex, ClientDto clientDto, Model model) {
        String error = ex.getMessage();
        model.addAttribute("exception", error);
        model.addAttribute("clientDto", clientDto);
        return "signup-form";
    }

    private Client getClientFromClientDto(ClientDto clientDto){

        List<AddressDto> add = clientDto.getAddressList();
        List<PhoneNumberDto> phones = clientDto.getPhoneNumberList();

        List<Address> addressList = add.stream().map(x -> addressMapper.fromDTO(x)).collect(Collectors.toList());
        List<PhoneNumber> phoneList = phones.stream().map(x -> phoneNumberMapper.fromDTO(x)).collect(Collectors.toList());

        Client client = clientMapper.fromDTO(clientDto);

        client.getAddressList().add(addressList.get(0));

        client.getPhoneNumberList().add(phoneList.get(0));

        return client;
    }
}
