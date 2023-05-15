package com.example.filrougefo.service.order;

import com.example.filrougefo.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService implements IntOrderService{
    private final OrderRepository orderRepository;


}
