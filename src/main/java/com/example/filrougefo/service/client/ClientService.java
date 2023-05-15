package com.example.filrougefo.service.client;

import com.example.filrougefo.entity.Category;
import com.example.filrougefo.entity.Client;
import com.example.filrougefo.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService implements IntClientService {
    private final ClientRepository clientRepository;
    @Override
    public Client findById(Long id) {

        Client client = clientRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("No client found for Id:"+id));

        return client;
    }
    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(Client client) {

        return clientRepository.save(client);
    }

    @Override
    public void updateClient(Client client) {

        if(client.getId()==0){
            throw new RuntimeException("Cannot update a nonexistent client. The client object passed as argument has an id="+client.getId());
        }
        clientRepository.save(client);
    }
}
