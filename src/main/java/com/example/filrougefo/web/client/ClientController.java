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
import org.springframework.web.bind.annotation.ExceptionHandler;
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
        clientDto.getAddressList().add(new Address());
        clientDto.getPhoneNumberList().add(new PhoneNumber());
        String error = "";
        ModelAndView mav = new ModelAndView();
        mav.addObject("client",clientDto);
        mav.addObject("error", error);
        mav.setViewName("signup-form");
        return mav;
    }
    @PostMapping("client/register")
    public String addNewClient(@ModelAttribute("client") @Valid ClientDto clientDto,
                               BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("errors",bindingResult.getAllErrors());
            return "signup-form";
        }

        Client client = clientMapper.fromDTO(clientDto);
        Client newClient = clientService.registerNewAccount(client.getEmail());
        clientService.createClient(newClient);
        return "success-signup";
    }


    @ExceptionHandler(value = {ClientAlreadyExistException.class})
    public ModelAndView handleRegistration(@ModelAttribute("client") ClientDto client, @ModelAttribute("error") String error,ClientAlreadyExistException ex){

        ModelAndView mav = new ModelAndView();
        error = ex.getMessage();

        mav.addObject("error",error);
        mav.addObject("client",client);
        mav.setViewName("signup-form");
        return mav;
    }



}
