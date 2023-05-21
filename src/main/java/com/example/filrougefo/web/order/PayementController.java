package com.example.filrougefo.web.order;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/payment")
public class PayementController {

    @GetMapping
    public String getPaymentForm(Model model){
        PaymentDto paymentDto = new PaymentDto();
        model.addAttribute("paymentDto",paymentDto);
        return "payment";
    }

    @PostMapping
    public String confirmPayement(
            @ModelAttribute("paymentDto") @Valid PaymentDto paymentDto,
            t addBindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "payment";
        }

        return "success-order";
    }
}
