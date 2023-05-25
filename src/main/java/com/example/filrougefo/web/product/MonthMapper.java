package com.example.filrougefo.web.product;

import com.example.filrougefo.entity.Month;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MonthMapper {

    MonthDTO toDTO(Month month);

    @Mapping(target = "products", ignore = true)
    Month fromDTO(MonthDTO monthDTO);

}
