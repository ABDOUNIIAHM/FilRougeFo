package com.example.filrougefo.service.client;

import com.example.filrougefo.entity.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IntClientService {
    Client findById(long id);
    List<Client> findAll();
    boolean isValidEmail(String email);
    boolean isValidUpdatedEmail(String email, long id);
    Client createClient(Client client);
    void updateClient(Client client);
    void updateClientInformation(long clientId, String email, String firstName, String lastName, String avatarUrl);
    Client getClientById(long clientId);


}
