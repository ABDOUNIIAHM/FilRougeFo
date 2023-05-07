package com.example.filrougefo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.util.List;

@Entity
@Table(name = "months")
@Immutable
@Data @NoArgsConstructor @AllArgsConstructor
public class Month {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany
    @JoinTable(name = "product_months",
                joinColumns = @JoinColumn(name = "idProduct"),
                inverseJoinColumns = @JoinColumn(name = "idMonth"))
    private List<Product> products;
}
