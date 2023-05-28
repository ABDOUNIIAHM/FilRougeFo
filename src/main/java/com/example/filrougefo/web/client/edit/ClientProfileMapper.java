package com.example.filrougefo.web.client.edit;

import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.Order;
import com.example.filrougefo.web.client.ClientDto;
import com.example.filrougefo.web.order.OrderDto;
import com.example.filrougefo.web.order.OrderLineDto;
import org.aspectj.lang.annotation.After;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientProfileMapper {

    ClientProfileDTO toDTO(Client client);

    @Mapping(target = "password", ignore = true)
    Client fromDTO(ClientProfileDTO clientDto);

}
