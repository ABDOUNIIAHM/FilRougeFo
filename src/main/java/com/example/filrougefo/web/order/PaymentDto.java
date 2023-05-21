package com.example.filrougefo.web.order;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class PaymentDto {
    @Pattern(regexp = "^[0-9]{16}$", message = "invalid card number")
    private String cardNumber;
    @Pattern(regexp = "^[A-Za-z]{3,20}$", message = "invalid name")
    private String cardHolder;
    @Future(message = "Your card has expired")
    private LocalDate expirationDate;
    @Pattern(regexp = "^[0-9]{3}$", message = "invalid cvv")
    private String cvv;
}
