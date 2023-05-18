package com.example.filrougefo.web.Admin;
import com.example.filrougefo.admin.AdminDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class ApiController {

    private final RestTemplate restTemplate;

    @Autowired
    public ApiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/contact")
    public String fetchApiData(Model model) {
        String apiUrl = "http://localhost:8080/webapi/admin"; //  l'URL  API
       List<AdminDto> apiData = restTemplate.getForObject(apiUrl, List.class);

        //  les données récupérées
        model.addAttribute("apiData", apiData);

        return "contact-form"; //  vue Thymeleaf
    }
    @PostMapping("/contact")
    public String sendMail(@RequestParam("email") String email, @RequestParam("admin") String adminEmail, @RequestParam("message") String message, Model model) {

        // Exemple de traitement
        System.out.println("E-mail envoyé à l'adresse : " + email);
        System.out.println("Administrateur sélectionné : " + adminEmail);
        System.out.println("Contenu du message : " + message);

        model.addAttribute("adminEmail", adminEmail);
        return "redirect:/contact";
    }

}
