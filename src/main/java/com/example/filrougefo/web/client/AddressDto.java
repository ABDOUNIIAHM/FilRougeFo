package com.example.filrougefo.web.client;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @EqualsAndHashCode
@ToString
public class AddressDto {
    private long id;

    // "Résidence principale" par ex
    @NotNull(message = "Ne doit pas être vide.")
    @NotEmpty(message = "Ne doit pas être vide.")
    @Size(max = 50, message = "50 caractères maximum.")
    @Pattern(regexp = "^[\\w \\p{L}}-]{0,50}$", message = "Entrée non valide.")
    private String title;

    // "3bis, "1345ter", "3"
    @NotNull(message = "Ne doit pas être vide.")
    @NotEmpty(message = "Ne doit pas être vide.")
    @Size(max = 10, message = "10 caractères maximum.")
    @Pattern(regexp = "\\d+(?:\\w+)?", message = "Entrée non valide, alphabet latin uniquement.")
    private String number;

    // "rue" "avenue" "boulevard"
    @NotNull(message = "Ne doit pas être vide")
    @NotEmpty(message = "Ne doit pas être vide")
    @Size(max = 20, message = "20 caractères maximum.")
    @Pattern(regexp = "[\\w\\p{L}-]+$", message = "Entrée non valide. Attendu du type: rue, boulevard etc.")
    private String roadPrefix;
    @NotNull(message = "Ne doit pas être vide")
    @NotEmpty(message = "Ne doit pas être vide")
    @Size(max = 50, message = "50 caractères maximum.")
    @Pattern(regexp = "[\\w\\p{L}- ]+$", message = "Entrée non valide, alphabet Latins uniquement.")
    private String roadName;

    @Size(max = 50, message = "50 caractères maximum.")
    @Pattern(regexp = "[\\w\\p{L}- ]*$", message = "Entrée non valide, alphabet Latins uniquement.")
    private String complement;
    @NotNull(message = "Ne doit pas être vide")
    @NotEmpty(message = "Ne doit pas être vide")
    @Pattern(regexp = "\\d{5}", message = "Code postal invalide.")
    private String zipCode;
    @NotNull(message = "Ne doit pas être vide")
    @NotEmpty(message = "Ne doit pas être vide")
    @Size(max = 50, message = "50 caractères maximum.")
    @Pattern(regexp = "[\\w\\p{L}- ]+$", message = "Entrée non valide, alphabet Latins uniquement.")
    private String city;
}
