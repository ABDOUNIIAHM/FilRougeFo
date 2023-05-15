package com.example.filrougefo.service.client;

import com.example.filrougefo.repository.CategoryRepository;
import com.example.filrougefo.repository.ClientRepository;
import com.example.filrougefo.service.category.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientService underTest;

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void createClient() {
    }
}