package com.example.filrougefo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Admins")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String username;
    private boolean isSuperAdmin=false;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
