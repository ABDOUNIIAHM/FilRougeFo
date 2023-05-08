package com.example.filrougefo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String avatarUrl;
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();
    @OneToMany(mappedBy = "client")
    private List<Address> addresses = new ArrayList<>();
}
