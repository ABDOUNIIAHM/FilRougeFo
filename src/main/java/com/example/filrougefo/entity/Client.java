package com.example.filrougefo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Clients")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;
    private String password;
    private String avatarUrl;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<Order> orderList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_client")
    private List<Address> addressList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_client")
    private List<PhoneNumber> phoneNumberList = new ArrayList<>();
}
