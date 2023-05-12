package com.example.filrougefo.service.client;

import com.example.filrougefo.entity.Client;
import com.example.filrougefo.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IntClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }
    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
