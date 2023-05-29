package com.example.filrougefo.web.client.edit;

import com.example.filrougefo.web.client.validation.MatchingPassword;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @EqualsAndHashCode @ToString
@MatchingPassword(message = "ancien mot de pass incorrect")
public class EditPasswordDto {
    private String password;
    private String matchingPassword;
    @NotNull(message = "Ne doit pas être vide.")
    @NotEmpty(message = "Ne doit pas être vide.")
    @Size(max = 50, message = "50 caractères maximum.")
    @Pattern(regexp = "^[\\w\\p{L}0-9!@#$%^&*()_\\-]+$", message = "Entrée non valide.")
    private String newPassword;

}
