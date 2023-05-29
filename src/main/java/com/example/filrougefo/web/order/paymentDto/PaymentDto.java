package com.example.filrougefo.web.order.paymentDto;

import jakarta.validation.Valid;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class PaymentDto {
    private long id;
    @Valid
    private BankTransferDto bankTransferDto;
    @Valid
    private CardPaymentDto cardPaymentDto;
    @Valid
    private PaypalPayementDto paypalPayementDto;
}
