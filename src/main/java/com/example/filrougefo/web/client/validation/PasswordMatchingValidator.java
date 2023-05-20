package com.example.filrougefo.web.client.validation;

import com.example.filrougefo.web.client.ClientDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchingValidator implements ConstraintValidator<MatchingPassword, Object> {
    @Override
    public void initialize(MatchingPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        ClientDto client = (ClientDto) o;
        return client.getPassword().equals(client.getMatchingPassword());
    }
}
