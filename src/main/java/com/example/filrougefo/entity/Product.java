package com.example.filrougefo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String unit;
    private BigDecimal pricePerUnit;
    private String imgUrl;
    private BigDecimal vat;
    private String description;
    private BigDecimal stock;
    @ManyToOne
    @JoinColumn(name = "idCategory")
    private Category category;
    @ManyToMany
    @JoinTable(name = "product_months",
            joinColumns = @JoinColumn(name = "idProduct"),
            inverseJoinColumns = @JoinColumn(name = "idMonth"))
    private List<Month> seasonalMonths = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<OrderLine> orderLines = new ArrayList<>();

}
