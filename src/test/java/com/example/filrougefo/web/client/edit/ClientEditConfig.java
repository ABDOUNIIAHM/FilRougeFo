package com.example.filrougefo.web.client.edit;

import com.example.filrougefo.web.client.*;
import com.example.filrougefo.web.order.OrderMapper;
import com.example.filrougefo.web.order.OrderMapperImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ClientEditConfig {

    @Bean
    ClientProfileMapper clientProfileMapper(){
        return new ClientProfileMapperImpl();
    }
    @Bean
    AddressMapper addressMapper(){
        return new AddressMapperImpl();
    }
    @Bean
    ClientMapper clientMapper(){
        return new ClientMapperImpl();
    }
    @Bean
    PhoneNumberMapper phoneNumberMapper(){
        return new PhoneNumberMapperImpl();
    }
    @Bean
    OrderMapper orderMapper(){
        return new OrderMapperImpl();
    }
}
