package com.example.filrougefo.web.client;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ClientControllerTestConfiguration {

    @Bean
    ClientMapper clientMapper(){
        return new ClientMapperImpl();
    }
    @Bean
    AddressMapper addressMapper(){
        return new AddressMapperImpl();
    }
    @Bean
    PhoneNumberMapper phoneNumberMapper(){
        return new PhoneNumberMapperImpl();
    }
}
