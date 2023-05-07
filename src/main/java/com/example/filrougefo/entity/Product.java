package com.example.filrougefo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Data @NoArgsConstructor @AllArgsConstructor
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
    @ManyToMany(mappedBy = "products")
    private List<Month> seasonalMonths;

}
