package com.example.filrougefo.web.client;

import com.example.filrougefo.entity.Address;
import com.example.filrougefo.entity.Client;
import com.example.filrougefo.service.client.IntClientService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ClienDetail {

        private IntClientService clientService;

        private ClientMapper clientMapper;
        private AddressMapper addressMapper;
        private PasswordEncoder passwordEncoder;
        private PhoneNumberMapper phoneNumberMapper;

    @GetMapping("/client/detail")
    public String geDetailProfil(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        ClientDto clientDto = clientService.getClientDtoByUsername(username);

//        List<Address> addresses = clientService.findAddressesByClientId(clientId);
//
        // Ajoutez les adresses au modèle pour les utiliser dans votre vue

        model.addAttribute("clientDto", clientDto);
        return "profil-detail";
    }


}
