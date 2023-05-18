package com.example.filrougefo.web.client;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class AddressDto {
    private long id;
    private String title;
    @NotNull
    @NotEmpty
    private String number;
    private String roadPrefix;
    @NotNull
    @NotEmpty
    private String roadName;
    private String complement;
    @NotNull
    @NotEmpty
    private String zipCode;
    @NotNull
    @NotEmpty
    private String city;
}
