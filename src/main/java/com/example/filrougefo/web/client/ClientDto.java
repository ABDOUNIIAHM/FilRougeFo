package com.example.filrougefo.web.client;

import com.example.filrougefo.entity.Address;
import com.example.filrougefo.entity.Order;
import com.example.filrougefo.entity.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ClientDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String avatarUrl;
    private List<Order> orderList = new ArrayList<>();
    private List<Address> addressList = new ArrayList<>();
    private List<PhoneNumber> phoneNumberList;
}
