package com.example.filrougefo.web.client;

import com.example.filrougefo.entity.Address;
import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.PhoneNumber;
import com.example.filrougefo.repository.AddressRepository;
import com.example.filrougefo.security.ClientAuthDetail;
import com.example.filrougefo.service.address.AddressService;
import com.example.filrougefo.service.client.IntClientService;
import com.example.filrougefo.service.phonenumber.PhoneNumberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ClienDetail {

    private IntClientService clientService;
    private AddressRepository addressRepository;
    private AddressService addressService;
    private ClientMapper clientMapper;
    private ClientAuthDetail authenticatedClient;
    private AddressMapper addressMapper;
    private PhoneNumberService phoneNumberService;
    private PasswordEncoder passwordEncoder;
    private PhoneNumberMapper phoneNumberMapper;

    @GetMapping("/client/detail")
    public String geDetailProfil(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Client client = authenticatedClient.getClient();
        System.out.println(client.getAvatarUrl() + "en get (1)");
        String username = userDetails.getUsername();
        long clientId = clientService.getClientIdByUsername(username);

        ClientDto clientDton = clientService.getClientDtoByUsername(username);
        List<Address> listAdresse = addressService.findAddressesByClient(client);

        List<PhoneNumber> phoneNumberList = phoneNumberService.findPhoneNumberByClient(client);
        List<PhoneNumberDto> phoneNumberDtoList = phoneNumberList.stream().map(phoneNumberMapper::toDTO).collect(Collectors.toList());
        List<AddressDto> adressedto = listAdresse.stream().map(addressMapper::toDTO).collect(Collectors.toList());

        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setEmail(client.getEmail());
        clientDto.setPassword(client.getPassword());
        clientDto.setAvatarUrl(client.getAvatarUrl());
        clientDto.setOrderList(client.getOrderList());
        clientDto.setAddressList(adressedto);
        clientDto.setPhoneNumberList(phoneNumberDtoList);
        System.out.println(clientDto.getAvatarUrl() + "en get (2)");
        model.addAttribute("client", client);
        model.addAttribute("clientDton", clientDton);
        model.addAttribute("listAdresse", listAdresse);
        model.addAttribute("phoneNumberList", phoneNumberList);
        return "profil-detail";
    }


    @PostMapping("/client/update")
    public String updateClient(@ModelAttribute("clientDto") ClientDto updatedClient) {
        long clientId = updatedClient.getId();
        String email = updatedClient.getEmail();
        String firstName = updatedClient.getFirstName();
        String lastName = updatedClient.getLastName();
        String avatarUrl = updatedClient.getAvatarUrl();
        System.out.println(avatarUrl + "modifier post");

        clientService.updateClientInformation(clientId, email, firstName, lastName, avatarUrl);

        return "redirect:/client/detail";
    }

    @PostMapping("/client/addresses")
    public String updateClientAddresses(@RequestParam("addressId") List<Long> addressIds,
                                        @RequestParam("addressTitle") List<String> titles,
                                        @RequestParam("addressRoadPrefix") List<String> roadPrefixes,
                                        @RequestParam("addressRoadName") List<String> roadNames,
                                        @RequestParam("addressCity") List<String> cities,
                                        @RequestParam("addressNumber") List<String> numbers,
                                        @RequestParam("addressComplement") List<String> complements,
                                        @RequestParam("addressZipCode") List<String> zipCodes) {
        for (int i = 0; i < addressIds.size(); i++) {
            long addressId = addressIds.get(i);
            String title = titles.get(i);
            String roadPrefix = roadPrefixes.get(i);
            String roadName = roadNames.get(i);
            String city = cities.get(i);
            String number = numbers.get(i);
            String complement = complements.get(i);
            String zipCode = zipCodes.get(i);
            System.out.println(addressId);
            System.out.println(title);
            System.out.println(roadPrefix);
            System.out.println(roadName);
            System.out.println(city);
            System.out.println(number);
            System.out.println(complement);
            System.out.println(zipCode);
            addressService.updateAddress(addressId, title, roadPrefix, roadName, city, number, complement, zipCode);
        }

        return "redirect:/client/detail";
    }

    @PostMapping("/client/phoneNumbers")
    public String updateClientPhoneNumbers(@RequestParam("phoneNumberId") List<Long> phoneNumberIds,
                                           @RequestParam("phoneNumberValue") List<String> phoneNumber) {
        for (int i = 0; i < phoneNumberIds.size(); i++) {
            long phoneNumberId = phoneNumberIds.get(i);
            String value = phoneNumber.get(i);
            phoneNumberService.updatePhoneNumber(phoneNumberId, value);
        }

        return "redirect:/client/detail";
    }

}



