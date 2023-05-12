package com.example.filrougefo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PhoneNumbers")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Data
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;

}