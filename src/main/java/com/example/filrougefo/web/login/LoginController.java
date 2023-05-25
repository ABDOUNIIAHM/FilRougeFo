package com.example.filrougefo.web.login;


import com.example.filrougefo.entity.Client;
import com.example.filrougefo.web.client.ClientDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginForm(Model model){

     model.addAttribute("client",new Client());

        return "login-form";
    }
}
