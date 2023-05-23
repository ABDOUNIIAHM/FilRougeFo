package com.example.filrougefo.service.client;

import com.example.filrougefo.entity.Address;
import com.example.filrougefo.entity.Category;
import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.PhoneNumber;
import com.example.filrougefo.exception.ClientAlreadyExistException;
import com.example.filrougefo.exception.ClientNotFoundException;
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

@Service
@AllArgsConstructor
public class ClientService implements IntClientService {
    private final ClientRepository clientRepository;
    @Override
    public Client findById(long id) {

        Client client = clientRepository
                .findById(id)
                .orElseThrow(()-> new ClientNotFoundException("No client found for Id:"+id));

        return client;
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

}
