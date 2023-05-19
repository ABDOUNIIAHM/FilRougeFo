package com.example.filrougefo.web.client;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class PhoneNumberDto {
    private long id;
    @NotNull(message = "")
    @NotEmpty(message = "")
    @Pattern(regexp = "\\d{10}", message = "invalid phone number")
    private String phoneNumber;
}
