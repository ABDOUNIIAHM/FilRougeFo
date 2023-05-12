package com.example.filrougefo.service.client;

import com.example.filrougefo.entity.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IntClientService {
    Optional<Client> findById(Long id);
    List<Client> findAll();


}
