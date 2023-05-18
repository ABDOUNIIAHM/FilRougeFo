package com.example.filrougefo.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
    private int idAdmin;
    private String username;
    private boolean isSuperAdmin;
    private String firstName;
    private String lastName;
    private String email;
}
