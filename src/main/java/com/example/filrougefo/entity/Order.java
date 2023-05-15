package com.example.filrougefo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_status")
    private OrderStatus status;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "id_paymentMethod")
    private PaymentMethod paymentMethod;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderLine> orderLines = new ArrayList<>();
}
