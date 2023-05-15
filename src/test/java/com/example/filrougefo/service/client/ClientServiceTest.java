package com.example.filrougefo.service.client;
import com.example.filrougefo.entity.Category;
import com.example.filrougefo.entity.Client;
import com.example.filrougefo.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientService underTest;
    @Test
    void ShouldReturnAllClients() {

        Client client1 = new Client();
        Client client2 = new Client();
        client1.setId(1L);
        client2.setId(2L);

        List<Client> expected = List.of(client1,client2);

        when(clientRepository.findAll()).thenReturn(expected);
        List<Client> result = underTest.findAll();

        assertEquals(expected,result);
    }

    @Test
    void ShouldReturnAClientGivenId() {

        Client expected = new Client();
        expected.setId(1L);

        when(clientRepository.findById(any(long.class))).thenReturn(Optional.of(expected));
        Client result = underTest.findById(1L);

        assertTrue(result instanceof Client);
        assertEquals(expected,result);
    }
    @Test
    void ShouldThrowExceptionWhenClientNotFoundById(){

        when(clientRepository.findById(any(long.class))).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> underTest.findById(1L));
    }

    @Test
    void ShouldCreateAClientAndReturnCreatedClient() {

    }
}