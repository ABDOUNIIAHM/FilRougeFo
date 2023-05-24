package com.example.filrougefo.service.client;

import com.example.filrougefo.entity.Address;
import com.example.filrougefo.entity.Category;
import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.PhoneNumber;
import com.example.filrougefo.exception.AddressNotFoundException;
import com.example.filrougefo.exception.ClientAlreadyExistException;
import com.example.filrougefo.exception.ClientNotFoundException;
import com.example.filrougefo.repository.AddressRepository;
import com.example.filrougefo.repository.ClientRepository;
import com.example.filrougefo.web.client.AddressDto;
import com.example.filrougefo.web.client.ClientDto;
import com.example.filrougefo.web.client.PhoneNumberDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService implements IntClientService {
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    @Override
    public Client findById(long id) {

        Client client = clientRepository
                .findById(id)
                .orElseThrow(()-> new ClientNotFoundException("No client found for Id:"+id));

        return client;
    }

    @Override
    public Client getClientById(long clientId){
        return clientRepository.getClientById(clientId);
    }

    public ClientDto getClientDtoByUsername(String email) {
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " +email));

        ClientDto clientDto = new ClientDto();
        clientDto.setEmail(client.getEmail());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setPassword(client.getPassword());
        clientDto.setAvatarUrl(client.getAvatarUrl());
        clientDto.setOrderList(client.getOrderList());

        // Mapper les adresses
        List<AddressDto> addressDtoList = new ArrayList<>();
        for (Address address : client.getAddressList()) {
            AddressDto addressDto = new AddressDto();
            addressDto.setRoadName(address.getRoadName());
            addressDto.setCity(address.getCity());
//            addressDto.setId(address.getId());
            addressDto.setNumber(address.getNumber());
            addressDto.setTitle(address.getTitle());
            addressDto.setComplement(address.getComplement());
            addressDto.setZipCode(address.getZipCode());
            addressDtoList.add(addressDto);
        }
        clientDto.setAddressList(addressDtoList);

        // les numéros de téléphone
        List<PhoneNumberDto> phoneNumberDtoList = new ArrayList<>();
        for (PhoneNumber phoneNumber : client.getPhoneNumberList()) {
            PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
            phoneNumberDto.setPhoneNumber(phoneNumber.getPhoneNumber());

            phoneNumberDtoList.add(phoneNumberDto);
        }
        clientDto.setPhoneNumberList(phoneNumberDtoList);

        // Définissez d'autres propriétés du ClientDto si nécessaire

        return clientDto;
    }


    @Override
    public long getClientIdByUsername(String username) {
        Client client = clientRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return client.getId();
    }
    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public boolean isValidEmail(String email) {

        Optional<Client> optClient = clientRepository.findByEmail(email);
        if(optClient.isPresent()){
            throw new ClientAlreadyExistException("Email has already been used !");
        }
        return true;
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void updateClient(Client client) {

        if(client.getId()==0){
            throw new ClientNotFoundException("Cannot update a nonexistent client. The client object passed as argument has an id="+client.getId());
        }
        clientRepository.save(client);
    }
@Override
    public List<Address> findAddressesByClientId(long clientId) {
        Client client = clientRepository
                .findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("No client found for Id: " + clientId));

        return client.getAddressList();
    }
    @Override
    public void updateClientInformation(long clientId, String email, String firstName, String lastName, String avatarUrl){
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException("No client found for Id:" + clientId));

        // MAJ du client
        client.setEmail(email);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setAvatarUrl(avatarUrl);

        // Enregistrer les modifi
        clientRepository.save(client);
    }


    public ClientDto getClientDtoWithDetailsByUsername(String username) {
        // Logique pour récupérer le client et ses informations à partir du nom d'utilisateur
        // ...
        ClientDto client = new ClientDto();
        // Autres attributs du ClientDto
        // Récupérer les informations du client
        long clientId = client.getId();
        String firstName = client.getFirstName();
        String lastName = client.getLastName();
        String email = client.getEmail();
        String password = client.getPassword();
        String avatarUrl = client.getAvatarUrl();

        // Récupérer les informations des PhoneNumber
        List<PhoneNumberDto> phoneNumberList = client.getPhoneNumberList().stream()
                .map(phoneNumber -> {
                    PhoneNumberDto phoneNumberDto = new PhoneNumberDto();
                    phoneNumberDto.setId(phoneNumber.getId());
                    phoneNumberDto.setPhoneNumber(phoneNumber.getPhoneNumber());
                    return phoneNumberDto;
                })
                .collect(Collectors.toList());

        // Récupérer les informations des Address
        List<AddressDto> addressList = client.getAddressList().stream()
                .map(address -> {
                    AddressDto addressDto = new AddressDto();
                    addressDto.setId(address.getId());
                    addressDto.setTitle(address.getTitle());
                    addressDto.setNumber(address.getNumber());
                    addressDto.setRoadPrefix(address.getRoadPrefix());
                    addressDto.setRoadName(address.getRoadName());
                    addressDto.setComplement(address.getComplement());
                    addressDto.setZipCode(address.getZipCode());
                    addressDto.setCity(address.getCity());
                    return addressDto;
                })
                .collect(Collectors.toList());

        // Créer et renvoyer le ClientDto avec toutes les informations
        ClientDto clientDto = new ClientDto();
        clientDto.setId(clientId);
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);
        clientDto.setEmail(email);
        clientDto.setPassword(password);
        clientDto.setAvatarUrl(avatarUrl);
        clientDto.setPhoneNumberList(phoneNumberList);
        clientDto.setAddressList(addressList);

        return clientDto;
    }





}
