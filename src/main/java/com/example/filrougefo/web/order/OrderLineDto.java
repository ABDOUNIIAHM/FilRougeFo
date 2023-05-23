package com.example.filrougefo.web.order;

import com.example.filrougefo.entity.Order;
import com.example.filrougefo.entity.Product;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @EqualsAndHashCode @ToString

public class OrderLineDto {
    private long id;
    private Order order;
    private Product product;
    private BigDecimal quantity;
    private BigDecimal discount;
}
