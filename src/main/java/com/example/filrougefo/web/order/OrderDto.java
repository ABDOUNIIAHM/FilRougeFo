package com.example.filrougefo.web.order;

import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.OrderLine;
import com.example.filrougefo.entity.OrderStatus;
import com.example.filrougefo.entity.PaymentMethod;
import lombok.*;

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
    private Client client;
    private List<OrderLine> orderLines = new ArrayList<>();

}
