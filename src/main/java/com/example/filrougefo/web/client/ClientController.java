package com.example.filrougefo.web.client;

import com.example.filrougefo.service.client.IntClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class ClientController {
    private IntClientService clientService;

    @GetMapping("client/register")
    public String getRegisterForm(){

        return "registration-form";
    }

}
