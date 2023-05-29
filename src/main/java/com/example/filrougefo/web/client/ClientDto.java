package com.example.filrougefo.web.client;


import com.example.filrougefo.entity.Order;

import com.example.filrougefo.web.client.validation.MatchingPassword;
import com.example.filrougefo.web.client.validation.ValidEmail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @EqualsAndHashCode @ToString
@MatchingPassword
public class ClientDto {
    private long id;
    @NotEmpty(message = "")
    @NotNull(message = "")
    @Pattern(regexp = "^[A-Za-z ]{3,15}+$", message = "invalid input")
    private String firstName;

    @NotEmpty(message = "")
    @NotNull(message = "")
    @Pattern(regexp = "^[A-Za-z ]{3,15}+$", message = "invalid input")
    private String lastName;

    @ValidEmail
    private String email;

    @NotEmpty(message = "")
    @NotNull(message = "")
    @Pattern(regexp = "^[A-Za-z0-9!@#$%^&*()_-]{3,15}$", message = "Invalid input")
    private String password;
    private String matchingPassword;
    private String avatarUrl;
    private List<Order> orderList = new ArrayList<>();
    @Valid
    private List<AddressDto> addressList = new ArrayList<>();
    @Valid
    private List<PhoneNumberDto> phoneNumberList = new ArrayList<>();

}
