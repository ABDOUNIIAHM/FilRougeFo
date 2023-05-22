package com.example.filrougefo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.annotation.SessionScope;


@Configuration
public class ClientSessionConfig {
    @Bean
    @SessionScope
    public ClientAuthDetail authenticatedClient() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (ClientAuthDetail) authentication.getPrincipal();
    }
}
