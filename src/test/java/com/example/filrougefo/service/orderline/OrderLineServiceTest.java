package com.example.filrougefo.service.orderline;

import com.example.filrougefo.entity.OrderLine;
import com.example.filrougefo.exception.OrderLineControllerException;
import com.example.filrougefo.repository.OrderLineRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderLineServiceTest {
    @Mock
    private OrderLineRepository orderLineRepository;
    @InjectMocks
    private OrderLineService underTest;

    @Test
    void ShouldReturnOrderLineGivenAnOrderLineId() {

        OrderLine expected = new OrderLine();
        expected.setId(1);

        when(orderLineRepository.findById(any(long.class))).thenReturn(Optional.of(expected));
        OrderLine result = underTest.findById(1);

        assertEquals(expected,result);
    }
    @Test
    void ShouldThrowExceptionGivenAnOrderLineId() {

        when(orderLineRepository.findById(any(long.class))).thenReturn(Optional.empty());

        assertThrows(OrderLineControllerException.class, () -> underTest.findById(1));
    }


    @Test
    void findAllOrderLinesByOrderId() {

        OrderLine o1 = new OrderLine();
        OrderLine o2 = new OrderLine();
        o1.setId(1);
        o2.setId(2);
        List<OrderLine> expected = List.of(o1,o2);

        when(orderLineRepository.findAllByOrder_Id(any(long.class))).thenReturn(Optional.of(expected));
        List<OrderLine> result = underTest.findAllOrderLinesByOrderId(1);

        assertEquals(expected,result);
    }

    @Test
    void findOrderLineByOrderIdAndProductId() {

        OrderLine expected = new OrderLine();
        expected.setId(1);

        when(orderLineRepository.findAllByOrder_IdAndProduct_Id(any(long.class),any(long.class))).thenReturn(Optional.of(expected));
        OrderLine result = underTest.findOrderLineByOrderIdAndProductId(1,1);

        assertEquals(expected,result);
    }
    @Test
    void ShouldThrowExceptionGivenAnOrderIdAndProductId() {

        when(orderLineRepository.findAllByOrder_IdAndProduct_Id(any(long.class),any(long.class))).thenReturn(Optional.empty());

        assertThrows(OrderLineControllerException.class, () -> underTest.findOrderLineByOrderIdAndProductId(1,1));
    }
}