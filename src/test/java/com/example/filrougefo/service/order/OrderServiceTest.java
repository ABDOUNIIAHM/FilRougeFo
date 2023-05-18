package com.example.filrougefo.service.order;

import com.example.filrougefo.entity.Order;
import com.example.filrougefo.entity.OrderStatus;
import com.example.filrougefo.exception.OrderNotFoundException;
import com.example.filrougefo.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderService underTest;

    @Test
    void ShouldReturnAllOrdersGivenClientId() {

        Order o1 = new Order();
        Order o2 = new Order();
        o1.setId(1);
        o2.setId(2);
        List<Order> expected = List.of(o1,o2);

        when(orderRepository.findAllByClientId(any(long.class))).thenReturn(expected);
        List<Order> result = underTest.findAllOrdersByClientId(1);

        assertEquals(expected,result);
    }
    @Test
    void ShouldReturnAllOrdersGivenAnOrderStatusId() {

        Order o1 = new Order();
        Order o2 = new Order();
        o1.setId(1);
        o2.setId(2);
        List<Order> expected = List.of(o1,o2);

        when(orderRepository.findAllByStatus_Id(any(long.class))).thenReturn(expected);
        List<Order> result = underTest.findAllOrdersByStatus_Id(1);

        assertEquals(expected,result);
    }
    @Test
    void ShouldReturnAllOrdersGivenAnOrderStatusName() {

        OrderStatus os = new OrderStatus(1,"PENDING");
        Order o1 = new Order();
        Order o2 = new Order();
        o1.setStatus(os);
        o2.setStatus(os);
        List<Order> expected = List.of(o1,o2);

        when(orderRepository.findAllByStatus_Name(any(String.class))).thenReturn(expected);
        List<Order> result = underTest.findAllOrdersByStatus_Name("test");

        assertEquals(expected,result);
    }
    @Test
    void ShouldReturnAllOrdersBeforeGivenDate() {

        LocalDate date = LocalDate.of(1996,5,18);
        Order o1 = new Order();
        Order o2 = new Order();
        o1.setId(1);
        o2.setId(2);
        List<Order> expected = List.of(o1,o2);

        when(orderRepository.findAllByDateIsLessThanEqual(any(LocalDate.class))).thenReturn(expected);
        List<Order> result = underTest.findAllByOrdersBeforeChosenDate(date);

        assertEquals(expected,result);
    }
    @Test
    void ShouldReturnOrderGivenAnOrderId() {

        Order expected = new Order();
        expected.setId(1);

        when(orderRepository.findById(any(long.class))).thenReturn(Optional.of(expected));
        Order result = underTest.findOrderById(1);

        assertEquals(expected,result);
    }
    @Test
    void ShouldThrowExceptionGivenAnOrderId() {

        when(orderRepository.findById(any(long.class)))
                .thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> underTest.findOrderById(1));
    }

}