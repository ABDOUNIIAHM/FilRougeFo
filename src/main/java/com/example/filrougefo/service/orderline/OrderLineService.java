package com.example.filrougefo.service.orderline;
import com.example.filrougefo.entity.OrderLine;
import com.example.filrougefo.exception.OrderLineControllerException;
import com.example.filrougefo.repository.OrderLineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderLineService implements IntOrderLineService {
    private final OrderLineRepository orderLineRepository;

    @Override
    public OrderLine findById(long id) {
        return orderLineRepository
                .findById(id)
                .orElseThrow(()
                        ->new OrderLineControllerException("No such order line found for id:"+id));
    }

    @Override
    public List<OrderLine> findAllOrderLinesByOrderId(long idOrder) {
        return orderLineRepository
                .findAllByOrder_Id(idOrder)
                .orElseThrow(()-> new OrderLineControllerException("No order line found for order_id:"+idOrder));
    }

    @Override
    public OrderLine findOrderLineByOrderIdAndProductId(long idOrder, long idProduct) {
        return orderLineRepository
                .findAllByOrder_IdAndProduct_Id(idOrder,idProduct)
                .orElseThrow(()->
                        new OrderLineControllerException("No order line found for order_id:"+idOrder+" and product_id:"+idProduct));
    }
}
