package com.example.filrougefo.web.order.paymentDto;

import jakarta.validation.constraints.*;
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
    @Pattern(regexp = "^[0-9]{16}$", message = "Votre numéro de carte doit être composé de 16 chiffres")
    private String cardNumber;
    @NotNull(message = "Ne doit pas être vide.")
    @NotEmpty(message = "Ne doit pas être vide.")
    @Size(max = 50, message = "50 caractères maximum.")
    @Pattern(regexp = "^[\\w \\p{L}}-]+$", message = "Entrée non valide, alphabet latin uniquement.")
    private String cardHolder;
    @NotNull(message = "")
    @Future(message = "Votre carte à expirée")
    private LocalDate expirationDate;
    @NotNull(message = "")
    @Pattern(regexp = "^[0-9]{3}$", message = "CVV invalide.")
    private String cvv;
}
