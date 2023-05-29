package com.example.filrougefo.web.client.edit;

import com.example.filrougefo.entity.Order;
import com.example.filrougefo.web.client.AddressDto;
import com.example.filrougefo.web.client.PhoneNumberDto;

import com.example.filrougefo.web.client.validation.MatchingPassword;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Data
@MatchingPassword
public class ClientPasswordDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String matchingPassword;
    @NotNull(message = "Ne doit pas être vide.")
    @NotEmpty(message = "Ne doit pas être vide.")
    @Size(max = 50, message = "50 caractères maximum.")
    @Pattern(regexp = "^[\\w\\p{L}0-9!@#$%^&*()_\\-]+$", message = "Entrée non valide.")
    private String newPassword;
    private String avatarUrl;
    private List<Order> orderList = new ArrayList<>();
    private List<AddressDto> addressList = new ArrayList<>();
    private List<PhoneNumberDto> phoneNumberList = new ArrayList<>();
}
