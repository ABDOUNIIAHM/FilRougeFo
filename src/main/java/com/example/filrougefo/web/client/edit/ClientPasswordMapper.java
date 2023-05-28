package com.example.filrougefo.web.client.edit;

import com.example.filrougefo.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientPasswordMapper {

    ClientPasswordDto toDTO(Client client);

    @Mapping(target = "password",source = "newPassword")
    Client fromDTO(ClientPasswordDto clientDto);
}
