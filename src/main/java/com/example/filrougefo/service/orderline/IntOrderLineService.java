package com.example.filrougefo.service.orderline;

import com.example.filrougefo.entity.OrderLine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IntOrderLineService {
    OrderLine findById(long id);
    List<OrderLine> findAllOrderLinesByOrderId(long idOrder);
    OrderLine findOrderLineByOrderIdAndProductId(long idOrder, long idProduct);
}
