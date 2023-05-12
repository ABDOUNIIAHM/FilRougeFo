package com.example.filrougefo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private OrderStatus status;
    private Date date;
    @OneToOne
    private PaymentMethod paymentMethod;
    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines = new ArrayList<>();

}
