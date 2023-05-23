package com.example.filrougefo.service.order;
import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.Order;
import com.example.filrougefo.entity.OrderLine;
import com.example.filrougefo.entity.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface IntOrderService {
    List<Order> findAllOrdersByClientId(long id);
    List<Order> findAllOrdersByStatus_Id(long id);
    List<Order> findAllOrdersByStatus_Name(String name);
    List<Order> findAllByOrdersBeforeChosenDate(LocalDate date);
    Order findOrderById(long id);
    OrderLine addProductToOrder(int productId, long quantity, Client client);

    List<Order> findAllOrders(Client client);

    Order hasPendingOrder(Client client);
    List<Order> getNonPendingOrders(Client client);
    boolean validateOrder(long orderId);


}
