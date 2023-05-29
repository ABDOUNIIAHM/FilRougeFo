package com.example.filrougefo.web.client.validation;

import com.example.filrougefo.web.client.ClientDto;
import com.example.filrougefo.web.client.edit.ClientPasswordDto;
import com.example.filrougefo.web.client.edit.EditPasswordDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class PasswordMatchingValidator implements ConstraintValidator<MatchingPassword, Object> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public void initialize(MatchingPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        if(o == null) {
            return false;
        }
        if(o instanceof ClientDto){
            ClientDto client = (ClientDto) o;
            return client.getPassword().equals(client.getMatchingPassword());
        }

        if(o instanceof EditPasswordDto){
            EditPasswordDto pass = (EditPasswordDto) o;
            return passwordEncoder.matches(pass.getMatchingPassword(),pass.getPassword());
        }
        return false;

    }
}
