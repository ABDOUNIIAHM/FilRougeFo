package com.example.filrougefo.web.client.edit;

import com.example.filrougefo.web.client.AddressDto;
import com.example.filrougefo.web.client.PhoneNumberDto;
import com.example.filrougefo.web.client.validation.ValidEmail;
import com.example.filrougefo.web.order.OrderDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ClientProfileDTO {
    private long id;
    @NotNull(message = "Ne doit pas être vide.")
    @NotEmpty(message = "Ne doit pas être vide.")
    @Size(max = 50, message = "50 caractères maximum.")
    @Pattern(regexp = "^[\\w \\p{L}}-]+$", message = "Entrée non valide.")
    private String firstName;

    @NotNull(message = "Ne doit pas être vide.")
    @NotEmpty(message = "Ne doit pas être vide.")
    @Size(max = 50, message = "50 caractères maximum.")
    @Pattern(regexp = "^[\\w \\p{L}}-]+$", message = "Entrée non valide, alphabet latin uniquement.")
    private String lastName;

    @ValidEmail
    private String email;

    private String avatarUrl;
    private List<OrderDto> orderList = new ArrayList<>();
    @Valid
    private List<AddressDto> addressList = new ArrayList<>();
    @Valid
    private List<PhoneNumberDto> phoneNumberList = new ArrayList<>();

}
