package com.example.filrougefo.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Months")
@Immutable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Months {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany(mappedBy = "seasonalMonths")
    private List<Product> products = new ArrayList<>();
}
