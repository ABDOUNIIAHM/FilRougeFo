package com.example.filrougefo.web.client.edit;

import com.example.filrougefo.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientProfileMapper {

    ClientProfileDTO toDTO(Client client);

    @Mapping(target = "password", ignore = true)
    Client fromDTO(ClientProfileDTO clientDto);

}
