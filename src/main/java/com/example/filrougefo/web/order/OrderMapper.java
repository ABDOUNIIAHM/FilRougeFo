package com.example.filrougefo.web.order;

import com.example.filrougefo.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDTO(Order order);
    Order fromDTO(OrderDto orderDto);
}
