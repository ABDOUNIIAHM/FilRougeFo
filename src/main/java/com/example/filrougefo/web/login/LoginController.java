package com.example.filrougefo.web.login;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class LoginController {

    @GetMapping("/")
    public String getLoginForm(Model model){

        //ClientDto clientDto = new ClientDto();

        return "login-form";
    }
}
