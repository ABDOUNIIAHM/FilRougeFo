package com.example.filrougefo.web.client;

import com.example.filrougefo.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto toDTO(Client client);
    @Mapping(target = "matchingPassword", ignore = true)
    Client fromDTO(ClientDto clientDto);
}
