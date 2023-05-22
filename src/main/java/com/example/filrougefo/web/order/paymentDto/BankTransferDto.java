package com.example.filrougefo.web.order.paymentDto;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class BankTransferDto {

    private long id;
    @Pattern(regexp = "^([A-Z]{2}[0-9]{2})([A-Z0-9]{4})([0-9]{7})([A-Z0-9]{0,16})$", message = "invalid IBAN")
    private String bankAccount;
}
