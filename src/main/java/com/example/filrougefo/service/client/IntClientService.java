package com.example.filrougefo.service.client;

import com.example.filrougefo.entity.Address;
import com.example.filrougefo.entity.Client;
import com.example.filrougefo.web.client.AddressDto;
import com.example.filrougefo.web.client.ClientDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IntClientService {
    Client findById(long id);
    List<Client> findAll();
    boolean isValidEmail(String email);
    boolean isValidUpdatedEmail(String email, long id);
    Client createClient(Client client);
    void updateClient(Client client);

    long getClientIdByUsername(String username);

    List<Address> findAddressesByClientId(long clientId);

    ClientDto getClientDtoByUsername(String username);

    void updateClientInformation(long clientId, String email, String firstName, String lastName, String avatarUrl);

    ClientDto getClientDtoWithDetailsByUsername(String username);

    Client getClientById(long clientId);


}
