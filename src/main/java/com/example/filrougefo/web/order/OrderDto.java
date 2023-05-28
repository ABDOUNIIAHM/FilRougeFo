package com.example.filrougefo.web.order;

import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.OrderLine;
import com.example.filrougefo.entity.OrderStatus;
import com.example.filrougefo.entity.PaymentMethod;
import com.example.filrougefo.web.client.ClientDto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @EqualsAndHashCode @ToString
public class OrderDto {
    private long id;
    private OrderStatus status;
    private LocalDate date;
    private PaymentMethod paymentMethod;
    private ClientDto client;
    private List<OrderLineDto> orderLines = new ArrayList<>();

    public BigDecimal computeTotal() {
        BigDecimal total = BigDecimal.valueOf(0);
        for (OrderLineDto orderLine : orderLines) {
            total = total.add(orderLine.computeTotal());
        }
        return total;
    }

}
