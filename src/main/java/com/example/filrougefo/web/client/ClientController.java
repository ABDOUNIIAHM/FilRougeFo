package com.example.filrougefo.web.client;


import com.example.filrougefo.entity.Address;
import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.PhoneNumber;
import com.example.filrougefo.exception.ClientAlreadyExistException;
import com.example.filrougefo.service.client.IntClientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
@AllArgsConstructor
public class ClientController {
    private IntClientService clientService;
    private ClientMapper clientMapper;
    private AddressMapper addressMapper;
    private PhoneNumberMapper phoneNumberMapper;

    @GetMapping("client/register")
    public ModelAndView getRegisterForm(){

        ClientDto clientDto = new ClientDto();
        clientDto.getAddressList().add(new AddressDto());
        clientDto.getPhoneNumberList().add(new PhoneNumberDto());
        String error = "";
        ModelAndView mav = new ModelAndView();
        mav.addObject("client",clientDto);
        mav.addObject("exception", error);
        mav.setViewName("signup-form");
        return mav;
    }
    @PostMapping("client/register")
    public String addNewClient(@ModelAttribute("client") @Valid ClientDto clientDto,
                               BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){

            return "signup-form";
        }

        model.addAttribute("client", clientDto);

        clientService.isValidEmail(clientDto.getEmail());

        Client client = getClientFromClientDto(clientDto);
        clientService.createClient(client);
        return "success-signup";
    }

    @ExceptionHandler(value = {ClientAlreadyExistException.class})
    public String handleClientRegistration(ClientAlreadyExistException ex, Model model){

        String error = ex.getMessage();
        //model.addAttribute("client",client);
        model.addAttribute("exception",error);
        return "signup-form";

    }


    private Client getClientFromClientDto(ClientDto clientDto){

        List<AddressDto> add = clientDto.getAddressList();
        List<PhoneNumberDto> phones = clientDto.getPhoneNumberList();

        List<Address> addressList = add.stream().map(x -> addressMapper.fromDTO(x)).collect(Collectors.toList());
        List<PhoneNumber> phoneList = phones.stream().map(x -> phoneNumberMapper.fromDTO(x)).collect(Collectors.toList());

        Client client = clientMapper.fromDTO(clientDto);

        addressList
                .stream()
                .map(x->client.getAddressList().add(x));

        phoneList
                .stream()
                .map(x->client.getPhoneNumberList().add(x));

        return client;
    }

}
