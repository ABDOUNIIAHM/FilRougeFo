package com.example.filrougefo.web.client;


import com.example.filrougefo.entity.Order;

import com.example.filrougefo.web.client.validation.MatchingPassword;
import com.example.filrougefo.web.client.validation.ValidEmail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @EqualsAndHashCode @ToString
@MatchingPassword
public class ClientDto {
    private long id;
    @NotNull(message = "Ne doit pas être vide.")
    @NotEmpty(message = "Ne doit pas être vide.")
    @Size(max = 50, message = "50 caractères maximum.")
    @Pattern(regexp = "^[\\w \\p{L}}-]+$", message = "Entrée non valide, alphabet latin uniquement.")
    private String firstName;

    @NotNull(message = "Ne doit pas être vide.")
    @NotEmpty(message = "Ne doit pas être vide.")
    @Size(max = 50, message = "50 caractères maximum.")
    @Pattern(regexp = "^[\\w \\p{L}}-]+$", message = "Entrée non valide, alphabet latin uniquement.")
    private String lastName;

    @ValidEmail
    private String email;

    @NotNull(message = "Ne doit pas être vide.")
    @NotEmpty(message = "Ne doit pas être vide.")
    @Size(max = 50, message = "50 caractères maximum.")
    @Pattern(regexp = "^[\\w\\p{L}0-9!@#$%^&*()_\\-]+$", message = "Entrée non valide.")
    private String password;
    private String matchingPassword;
    private String avatarUrl;
    private List<Order> orderList = new ArrayList<>();
    @Valid
    private List<AddressDto> addressList = new ArrayList<>();
    @Valid
    private List<PhoneNumberDto> phoneNumberList = new ArrayList<>();

}
