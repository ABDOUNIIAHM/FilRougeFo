package com.example.filrougefo.web.order.paymentDto;

import com.example.filrougefo.web.client.validation.ValidEmail;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class PaypalPayementDto {
    private long id;
    @ValidEmail
    private String paypalEmail;

}
