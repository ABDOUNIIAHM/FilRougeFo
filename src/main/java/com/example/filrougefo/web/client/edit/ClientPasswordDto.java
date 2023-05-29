package com.example.filrougefo.web.client.edit;

import com.example.filrougefo.entity.Order;
import com.example.filrougefo.web.client.AddressDto;
import com.example.filrougefo.web.client.PhoneNumberDto;
import com.example.filrougefo.web.client.validation.MatchingPassword;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotEmpty(message = "")
    @NotNull(message = "")
    @Pattern(regexp = "^[A-Za-z0-9!@#$%^&*()_-]{3,15}$", message = "Invalid input")
    private String newPassword;
    private String avatarUrl;
    private List<Order> orderList = new ArrayList<>();
    private List<AddressDto> addressList = new ArrayList<>();
    private List<PhoneNumberDto> phoneNumberList = new ArrayList<>();
}
