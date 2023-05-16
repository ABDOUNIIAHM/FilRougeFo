package com.example.filrougefo;
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
        String apiUrl = "http://localhost:8080/webapi/admin"; // Remplacez par l'URL de votre API
       List<AdminDto> apiData = restTemplate.getForObject(apiUrl, List.class);

        // Ajoutez les données récupérées à l'objet Model pour les transmettre à la vue
        model.addAttribute("apiData", apiData);

        return "contact-form"; // Le nom de la vue Thymeleaf
    }
}
