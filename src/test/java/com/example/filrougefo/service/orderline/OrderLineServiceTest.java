package com.example.filrougefo.service.orderline;

import com.example.filrougefo.repository.OrderLineRepository;
import com.example.filrougefo.repository.OrderRepository;
import com.example.filrougefo.service.order.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class OrderLineServiceTest {
    @Mock
    private OrderLineRepository orderLineRepository;
    @InjectMocks
    private OrderLineService underTest;

    @Test
    void ShouldReturnOrderLineGivenId() {
    }

    @Test
    void findAllOrderLinesByOrderId() {
    }

    @Test
    void findOrderLineByOrderIdAndProductId() {
    }
}