package com.example.filrougefo.service.order;
import com.example.filrougefo.entity.Order;
import com.example.filrougefo.entity.OrderLine;
import com.example.filrougefo.entity.OrderStatus;
import com.example.filrougefo.entity.Product;
import com.example.filrougefo.exception.OrderNotFoundException;
import com.example.filrougefo.repository.OrderLineRepository;
import com.example.filrougefo.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService implements IntOrderService{
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;

    @Override
    public List<Order> findAllOrdersByClientId(long id) {
        return orderRepository.findAllByClientId(id);
    }

    @Override
    public List<Order> findAllOrdersByStatus_Id(long id) {
        return orderRepository.findAllByStatus_Id(id);
    }

    @Override
    public List<Order> findAllOrdersByStatus_Name(String name) {
        return orderRepository.findAllByStatus_Name(name);
    }

    @Override
    public List<Order> findAllByOrdersBeforeChosenDate(LocalDate date) {
        return orderRepository.findAllByDateIsLessThanEqual(date);
    }

    @Override
    public Order findOrderById(long id){
        return orderRepository
                .findById(id)
                .orElseThrow(()-> new OrderNotFoundException("No such Order found for id:"+id));
    }

    @Override
    public OrderLine addProductToOrder(Product product, int quantity) {

        Order pendingOrder = hasPendingOrder();

        OrderLine orderLine = new OrderLine();
        orderLine.setOrder(pendingOrder);
        orderLine.setProduct(product);
        orderLine.setQuantity(BigDecimal.valueOf(quantity));

        OrderLine newOrderLine = orderLineRepository.save(orderLine);

        pendingOrder.getOrderLines().add(orderLine);
        orderRepository.save(pendingOrder);

        return newOrderLine;
    }

    private Order hasPendingOrder() {
        List<Order> orders = orderRepository.findAllByStatus_Name("PENDING");
        if(orders.size()==1){
            return orders.get(0);
        }
        Order order = new Order();
        return orderRepository.save(order);
    }
}
