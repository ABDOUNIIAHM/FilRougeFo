package com.example.filrougefo.service.client;

import com.example.filrougefo.entity.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IntClientService {
    Client findById(long id);
    List<Client> findAll();
    boolean isValidEmail(String email);
    Client createClient(Client client);
    void updateClient(Client client);
}
