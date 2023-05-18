package com.example.filrougefo.service.order;
import com.example.filrougefo.entity.Order;
import com.example.filrougefo.exception.OrderNotFoundException;
import com.example.filrougefo.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService implements IntOrderService{
    private final OrderRepository orderRepository;

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
}
