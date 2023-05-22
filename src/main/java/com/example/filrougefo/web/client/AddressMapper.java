package com.example.filrougefo.web.client;

import com.example.filrougefo.entity.Address;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDto toDTO(Address address);
    Address fromDTO(AddressDto addressDto);
}
