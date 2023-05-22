package com.example.filrougefo.web.order;

import com.example.filrougefo.entity.Order;
import com.example.filrougefo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor
@Data
public class OrderLineDto {
    private long id;
    private Order order;
    private Product product;
    private BigDecimal quantity;
    private BigDecimal discount;
}
