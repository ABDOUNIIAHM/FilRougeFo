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
public class AddressDto {
    private long id;

    @Pattern(regexp = "^[A-Za-z ]{0,25}+$", message = "invalid input")
    private String title;
    @NotNull(message = "")
    @NotEmpty(message = "")
    @Pattern(regexp = "\\d{1,3}", message = "invalid input")
    private String number;
    @Pattern(regexp = "[a-zA-Z]{0,3}", message ="3 characters max")
    private String roadPrefix;
    @NotNull(message = "")
    @NotEmpty(message = "")
    @Pattern(regexp = "^[A-Za-z ]{5,25}+$", message = "invalid input")
    private String roadName;

    @Pattern(regexp = "^[A-Za-z ]{0,25}+$", message = "invalid input")
    private String complement;
    @NotNull(message = "")
    @NotEmpty(message = "")
    @Pattern(regexp = "\\d{5}", message = "invalid zipcode")
    private String zipCode;
    @NotNull(message = "")
    @NotEmpty(message = "")
    @Pattern(regexp = "^[A-Za-z- ]{3,15}+$", message = "invalid input")
    private String city;
}
