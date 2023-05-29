package com.example.filrougefo.service.order;

import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;

@Service
public interface IntOrderService {
    List<Order> findAllOrdersByClientId(long id);
    List<Order> findAllOrdersByStatus_Id(long id);
    List<Order> findAllOrdersByStatus_Name(String name);
    List<Order> findAllByOrdersBeforeChosenDate(LocalDate date);
    Order findOrderById(long id);
    boolean addProductToOrder(int productId, double quantity, Client client);

    Order hasPendingOrder(Client client);
    List<Order> getNonPendingOrders(Client client);
    boolean validateOrder(long orderId);


}
