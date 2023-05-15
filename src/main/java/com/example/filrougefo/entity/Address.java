package com.example.filrougefo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Adresses")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String number;
    private String roadPrefix;
    private String roadName;
    private String complement;
    private String zipCode;
    private String city;

}
