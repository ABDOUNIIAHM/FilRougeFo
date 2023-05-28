package com.example.filrougefo.web.client.edit;

import com.example.filrougefo.entity.Address;
import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.Order;
import com.example.filrougefo.entity.PhoneNumber;
import com.example.filrougefo.exception.ClientAlreadyExistException;
import com.example.filrougefo.security.ClientAuthDetail;
import com.example.filrougefo.service.address.AddressService;
import com.example.filrougefo.service.client.IntClientService;
import com.example.filrougefo.service.order.OrderService;
import com.example.filrougefo.service.orderline.OrderLineService;
import com.example.filrougefo.service.phonenumber.PhoneNumberService;
import com.example.filrougefo.web.client.*;
import com.example.filrougefo.web.order.OrderMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@RequestMapping("/auth/client")
public class ClientEditController {
    private ClientAuthDetail authenticatedClient;
    private IntClientService clientService;
    private ClientProfileMapper clientProfileMapper;
    private AddressService addressService;
    private AddressMapper addressMapper;
    private PhoneNumberService phoneNumberService;
    private PhoneNumberMapper phoneNumberMapper;
    private OrderLineService orderLineService;
    private OrderService orderService;
    private OrderMapper orderMapper;

    @GetMapping("/detail")
    public String getClientDetail(Model model) {

        model.addAttribute("client", getClientProfileDTO());
        model.addAttribute("newPhoneNumber", new PhoneNumberDto());

        return "client/client-layout";
    }

    @GetMapping("/update")
    public String updateClientProfile(Model model) {

        model.addAttribute("client", getClientProfileDTO());
        model.addAttribute("newPhoneNumber", new PhoneNumberDto());
        model.addAttribute("isEditProfile", true);

        return "client/client-layout";
    }

    @PostMapping("/update")
    public String updateClientProfile(Model model, @ModelAttribute("client") @Valid ClientProfileDTO updatedClient, BindingResult bindingResult) {

        Client client = authenticatedClient.getClient();

        if (bindingResult.hasErrors()) {

            updatedClient.setAddressList(
                    addressService.findAddressesByClient(client)
                            .stream()
                            .map(addressMapper::toDTO)
                            .toList()
            );

            updatedClient.setPhoneNumberList(
                    phoneNumberService.findPhoneNumberByClient(client)
                            .stream()
                            .map(phoneNumberMapper::toDTO)
                            .toList()
            );

            updatedClient.setOrderList(
                    orderService.getNonPendingOrders(client)
                            .stream()
                            .map(orderMapper::toDTO)
                            .toList()
            );

            model.addAttribute("isEditProfile", true);

            return "client/client-layout";
        }

        try {

            clientService.isValidUpdatedEmail(updatedClient.getEmail(), updatedClient.getId());

            clientService.updateClientInformation(
                    updatedClient.getId(),
                    updatedClient.getEmail(),
                    updatedClient.getFirstName(),
                    updatedClient.getLastName(),
                    updatedClient.getAvatarUrl()
            );

            authenticatedClient.setClient(clientService.getClientById(updatedClient.getId()));

        } catch (ClientAlreadyExistException exception) {
            return handleClientUpdateError(exception, updatedClient, model);
        }

        return "redirect:/auth/client/detail";
    }

    @GetMapping("/phone/update/{id}")
    public String updatePhone(Model model, @PathVariable("id") long id) {

        PhoneNumber phoneNumberToUpdate = phoneNumberService.findById(id);

        model.addAttribute("client", getClientProfileDTO());
        model.addAttribute("isEditPhone", true);
        model.addAttribute("editedPhoneNumber", phoneNumberMapper.toDTO(phoneNumberToUpdate));
        model.addAttribute("id", id);

        return "client/client-layout";
    }

    @PostMapping("/phone/update/{id}")
    public String updatePhone(Model model, @ModelAttribute("editedPhoneNumber") @Valid PhoneNumberDto editedPhoneNumber, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("client", getClientProfileDTO());
            model.addAttribute("isEditPhone", true);
            model.addAttribute("editedPhoneNumber", editedPhoneNumber);
            model.addAttribute("id", editedPhoneNumber.getId());

            return "client/client-layout";
        }

        phoneNumberService.updatePhoneNumber(editedPhoneNumber.getId(), editedPhoneNumber.getPhoneNumber());

        return "redirect:/auth/client/detail";
    }

    @PostMapping("/phone/add")
    public String addPhone(Model model, @ModelAttribute("newPhoneNumber") @Valid PhoneNumberDto newPhoneNumber, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("client", getClientProfileDTO());
            model.addAttribute("newPhoneNumber", newPhoneNumber);
            model.addAttribute("addPhoneError", true);

            return "client/client-layout";
        }

        Client client = authenticatedClient.getClient();
        PhoneNumber phoneNumber = phoneNumberMapper.fromDTO(newPhoneNumber);
        phoneNumber.setClient(client);
        client.setPhoneNumberList(phoneNumberService.findPhoneNumberByClient(client));
        client.getPhoneNumberList().add(phoneNumber);
        clientService.updateClient(client);

        return "redirect:/auth/client/detail";
    }

    @GetMapping("/address/add")
    public String addressForm(Model model) {

        model.addAttribute("address", new AddressDto());

        return "client/address-form";
    }

    @GetMapping("/address/{id}")
    public String addressForm(Model model, @PathVariable("id") long id) {

        AddressDto updatedAddress = addressMapper.toDTO(addressService.findById(id));
        model.addAttribute("address", updatedAddress);

        return "client/address-form";
    }

    @PostMapping("/address")
    public String updateAddress(Model model, @ModelAttribute("address") @Valid AddressDto updatedAddress, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "client/address-form";
        }

        if (updatedAddress.getId() == 0) {

            Client client = authenticatedClient.getClient();
            Address address = addressMapper.fromDTO(updatedAddress);
            address.setClient(client);
            client.setAddressList(addressService.findAddressesByClient(client));
            client.getAddressList().add(address);
            clientService.updateClient(client);

        } else {
            addressService.updateAddress(
                    updatedAddress.getId(),
                    updatedAddress.getTitle(),
                    updatedAddress.getRoadPrefix(),
                    updatedAddress.getRoadName(),
                    updatedAddress.getCity(),
                    updatedAddress.getNumber(),
                    updatedAddress.getComplement(),
                    updatedAddress.getZipCode()
            );
        }

        return "redirect:/auth/client/detail";
    }

    private ClientProfileDTO getClientProfileDTO() {

        Client client = authenticatedClient.getClient();
        client.setAddressList(addressService.findAddressesByClient(client));
        client.setPhoneNumberList(phoneNumberService.findPhoneNumberByClient(client));
        client.setOrderList(orderService.getNonPendingOrders(client));
        for (Order order : client.getOrderList()) {
            order.setOrderLines(orderLineService.findAllOrderLinesByOrderId(order.getId()));
        }

        return clientProfileMapper.toDTO(client);
    }

    private String handleClientUpdateError(ClientAlreadyExistException ex, ClientProfileDTO clientDto, Model model) {
        String error = ex.getMessage();
        model.addAttribute("exception", error);
        model.addAttribute("clientDto", clientDto);
        return "redirect:/auth/client/detail";
    }



}
