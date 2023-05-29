package com.example.filrougefo.web.order;

import com.example.filrougefo.entity.OrderLine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderLineMapper {
    OrderLineDto toDTO(OrderLine orderLine);
    OrderLine fromDTO(OrderLineDto orderLineDto);
}
