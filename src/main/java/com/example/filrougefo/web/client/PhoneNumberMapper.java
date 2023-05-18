package com.example.filrougefo.web.client;

import com.example.filrougefo.entity.Address;
import com.example.filrougefo.entity.PhoneNumber;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneNumberMapper {
    PhoneNumberDto toDTO(PhoneNumber phone);
    PhoneNumber fromDTO(PhoneNumberDto phoneDto);
}
