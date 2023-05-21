package com.example.filrougefo.service.order;
import com.example.filrougefo.entity.Order;
import com.example.filrougefo.entity.OrderLine;
import com.example.filrougefo.entity.OrderStatus;
import com.example.filrougefo.entity.Product;
import com.example.filrougefo.exception.OrderNotFoundException;
import com.example.filrougefo.repository.OrderLineRepository;
import com.example.filrougefo.repository.OrderRepository;
import com.example.filrougefo.repository.ProductRepository;
import com.example.filrougefo.service.product.IntProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService implements IntOrderService{
    private final OrderRepository orderRepository;
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
    public OrderLine addProductToOrder(int productId, int quantity) {

        Product p = productService.findById(productId);

        Order pendingOrder = hasPendingOrder();

        OrderLine orderLine = new OrderLine();
        orderLine.setOrder(pendingOrder);
        orderLine.setProduct(p);
        orderLine.setQuantity(BigDecimal.valueOf(quantity));

        OrderLine newOrderLine = orderLineRepository.save(orderLine);

        pendingOrder.getOrderLines().add(orderLine);
        orderRepository.save(pendingOrder);

        return newOrderLine;
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order hasPendingOrder() {
        List<Order> orders = orderRepository.findAllByStatus_Name("PENDING");
        if(orders.size()==1){
            return orders.get(0);
        }
        Order order = new Order();
        return orderRepository.save(order);
    }
    @Override
    public boolean validateOrder(long id) {

        Optional<Order> toValidate = orderRepository.findById(id);

        if(toValidate.isPresent()){
            OrderStatus os = new OrderStatus();
            os.setId(2);
            Order order = toValidate.get();
            order.setStatus(os);
            orderRepository.save(order);
        }
        throw new OrderNotFoundException("No such order for id:"+id);
    }
}
