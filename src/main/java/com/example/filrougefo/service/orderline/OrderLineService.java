package com.example.filrougefo.service.orderline;

import com.example.filrougefo.entity.OrderLine;
import com.example.filrougefo.repository.OrderLineRepository;
import com.example.filrougefo.service.order.IntOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderLineService implements IntOrderLineService {
    private final OrderLineRepository orderLineRepository;

    @Override
    public OrderLine findById(long id) {
        return null;
    }

    @Override
    public List<OrderLine> findAllOrderLinesByOrderId(long idOrder) {
        return null;
    }

    @Override
    public OrderLine findOrderLineByOrderIdAndProductId(long idOrder, long idProduct) {
        return null;
    }
}
