package com.example.filrougefo.web.client;

import com.example.filrougefo.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto toDTO(Client client);
    @Mapping(source = "addressList" , target = "addressList", ignore = true)
    @Mapping(source = "phoneNumberList" , target = "phoneNumberList", ignore = true)
    Client fromDTO(ClientDto clientDto);
}
