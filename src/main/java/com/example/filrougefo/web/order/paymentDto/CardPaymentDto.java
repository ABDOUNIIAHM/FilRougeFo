package com.example.filrougefo.web.order.paymentDto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CardPaymentDto {
    private long id;
    @NotNull(message = "")
    @Pattern(regexp = "^[0-9]{16}$", message = "invalid card number")
    private String cardNumber;
    @NotNull(message = "")
    @Pattern(regexp = "^[A-Za-z]{3,20}$", message = "invalid name")
    private String cardHolder;
    @NotNull(message = "")
    @Future(message = "Your card has expired")
    private LocalDate expirationDate;
    @NotNull(message = "")
    @Pattern(regexp = "^[0-9]{3}$", message = "invalid cvv")
    private String cvv;
}
