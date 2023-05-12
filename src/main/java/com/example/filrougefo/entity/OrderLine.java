package com.example.filrougefo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
@Entity
@Table(name = "OrderLines")
@Data @NoArgsConstructor @AllArgsConstructor
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "idOrder")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "idProduct")
    private Product product;
    private int quantity;
    private BigDecimal discount;

}
