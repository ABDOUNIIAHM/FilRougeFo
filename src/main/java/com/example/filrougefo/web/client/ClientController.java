package com.example.filrougefo.web.client;

import com.example.filrougefo.entity.Address;
import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.PhoneNumber;
import com.example.filrougefo.service.client.IntClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@AllArgsConstructor
public class ClientController {
    private IntClientService clientService;
    private ClientMapper clientMapper;

    @GetMapping("client/register")
    public ModelAndView getRegisterForm(){

        ClientDto clientDto = new ClientDto();
        ModelAndView mav = new ModelAndView();

        mav.addObject("client",clientDto);
        //mav.addObject("address",new AddressDto());
        //mav.addObject("phone",new PhoneNumberDto());
        mav.setViewName("signup-form");

        return mav;
    }
    @PostMapping("client/register")
    public String addNewClient(@ModelAttribute("client") @Valid ClientDto clientDto){


        //ModelAndView mav = new ModelAndView();
        //clientDto.getAddressList().add(addressDto);
        //clientDto.getPhoneNumberList().add(phoneDto);

        Client client = clientMapper.fromDTO(clientDto);
        clientService.createClient(client);

        return "success-signup";
    }



}
