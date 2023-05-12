package com.example.filrougefo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Clients")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;
    private String password;
    private String avatarUrl;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_client")
    private List<Order> orderList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_client")
    private List<Address> addressList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_client")
    private List<PhoneNumber> phoneNumberList;
}
