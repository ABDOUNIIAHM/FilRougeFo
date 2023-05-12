package com.example.filrougefo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String number;
    private String roadPrefix;
    private String complement;
    private String zipCode;
    private String city;
    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;

}
