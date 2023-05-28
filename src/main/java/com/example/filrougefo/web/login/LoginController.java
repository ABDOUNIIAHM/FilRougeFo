package com.example.filrougefo.web.login;


import com.example.filrougefo.entity.Client;
import com.example.filrougefo.web.client.ClientDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginForm(@RequestParam(value = "error", required = false) String error,Model model) {
        if (error != null) {
            model.addAttribute("isError", true);
        } else {
            model.addAttribute("isError", false);
        }

        model.addAttribute("client",new ClientDto());

        return "login-form";
    }
}
