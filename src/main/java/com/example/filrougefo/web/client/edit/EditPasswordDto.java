package com.example.filrougefo.web.client.edit;

import com.example.filrougefo.web.client.validation.MatchingPassword;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @EqualsAndHashCode @ToString
@MatchingPassword(message = "ancien mot de pass incorrect")
public class EditPasswordDto {
    private String password;
    private String matchingPassword;
    @NotEmpty(message = "")
    @NotNull(message = "")
    @Pattern(regexp = "^[A-Za-z0-9!@#$%^&*()_-]{3,15}$", message = "caractères interdits")
    private String newPassword;

}
