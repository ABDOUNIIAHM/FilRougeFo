package com.example.filrougefo.service.order;
import com.example.filrougefo.entity.*;
import com.example.filrougefo.exception.OrderNotFoundException;
import com.example.filrougefo.repository.ClientRepository;
import com.example.filrougefo.repository.OrderLineRepository;
import com.example.filrougefo.repository.OrderRepository;
import com.example.filrougefo.repository.OrderStatusRepository;
import com.example.filrougefo.service.product.IntProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService implements IntOrderService{
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderLineRepository orderLineRepository;
    private IntProductService productService;

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
    public OrderLine addProductToOrder(int productId, double quantity,Client client) {

        Product p = productService.findById(productId);

        Order pendingOrder = hasPendingOrder(client);

        List<OrderLine> orderLines = pendingOrder.getOrderLines()
                .stream()
                .filter(ol -> ol.getProduct().getId() == productId)
                .toList();

        if(orderLines.size()==1){

            OrderLine orderLine = orderLines.get(0);
            Product product = orderLine.getProduct();
            product.setStock(product.getStock().subtract(BigDecimal.valueOf(quantity)));


        }


        OrderLine orderLine = new OrderLine();
        orderLine.setOrder(pendingOrder);
        orderLine.setProduct(p);
        orderLine.setQuantity(BigDecimal.valueOf(quantity));

        pendingOrder.getOrderLines().add(orderLine);
        orderRepository.save(pendingOrder);

        return orderLine;
    }

    @Override
    public List<Order> findAllOrders(Client client) {
        return orderRepository.findAllByClientId(client.getId());
    }

    @Override
    public Order hasPendingOrder(Client client) {

        List<Order> orders = orderRepository.findAllByStatus_NameAndAndClient_Id("PENDING",client.getId());

        if(orders.size()==1){
            return orders.get(0);
        }
        Order order = new Order();
        order.setClient(client);
        return orderRepository.save(order);

    }

    @Override
    public List<Order> getNonPendingOrders(Client client) {

        List<Order> allByClientId = orderRepository.findAllByClientId(client.getId());

        return allByClientId
                .stream()
                .filter(x-> x.getStatus().getId() != 1)
                .collect(Collectors.toList());
    }

    @Override
    public boolean validateOrder(long id) {

        Optional<Order> toValidate = orderRepository.findById(id);

        if(toValidate.isEmpty()){
            throw new OrderNotFoundException("No such order for id:"+id);
        }

        OrderStatus os = orderStatusRepository.findById(2L).get();
        Order order = toValidate.get();
        order.setStatus(os);

        orderRepository.save(order);
        return true;
    }
}
