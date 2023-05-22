package com.example.filrougefo.web.login;

import com.example.filrougefo.web.client.AddressDto;
import com.example.filrougefo.web.client.ClientDto;
import com.example.filrougefo.web.client.PhoneNumberDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginForm(Model model){

        //ClientDto clientDto = new ClientDto();

        return "login-form";
    }
}
