package com.example.filrougefo.web.client.edit;

import com.example.filrougefo.entity.Order;
import com.example.filrougefo.web.client.AddressDto;
import com.example.filrougefo.web.client.PhoneNumberDto;
import com.example.filrougefo.web.client.validation.ValidEmail;
import com.example.filrougefo.web.order.OrderDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    private String avatarUrl;
    private List<OrderDto> orderList = new ArrayList<>();
    @Valid
    private List<AddressDto> addressList = new ArrayList<>();
    @Valid
    private List<PhoneNumberDto> phoneNumberList = new ArrayList<>();

}
