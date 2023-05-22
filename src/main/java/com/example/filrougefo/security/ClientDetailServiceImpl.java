package com.example.filrougefo.security;

import com.example.filrougefo.entity.Client;
import com.example.filrougefo.exception.ClientNotFoundException;
import com.example.filrougefo.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientDetailServiceImpl implements UserDetailsService {
    private final ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

         Client user = clientRepository
                .findByEmail(email)
                .orElseThrow(() -> new ClientNotFoundException("No such client with email:" + email + " !"));

        return new ClientAuthDetail(user);
    }
}
