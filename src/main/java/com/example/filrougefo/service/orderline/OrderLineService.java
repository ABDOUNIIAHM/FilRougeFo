package com.example.filrougefo.service.orderline;

import com.example.filrougefo.repository.OrderLineRepository;
import com.example.filrougefo.service.order.IntOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderLineService implements IntOrderLineService {
    private final OrderLineRepository orderLineRepository;
}
