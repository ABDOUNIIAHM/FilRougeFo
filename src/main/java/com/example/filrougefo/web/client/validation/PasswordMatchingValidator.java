package com.example.filrougefo.web.client.validation;

import com.example.filrougefo.web.client.ClientDto;
import com.example.filrougefo.web.client.edit.ClientPasswordDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchingValidator implements ConstraintValidator<MatchingPassword, Object> {
    @Override
    public void initialize(MatchingPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        if(o==null) {
            return false;
        }
        if(o instanceof ClientDto){
            ClientDto client = (ClientDto) o;
            return client.getPassword().equals(client.getMatchingPassword());
        }

        if(o instanceof ClientPasswordDto){
            ClientPasswordDto client = (ClientPasswordDto) o;
            return client.getMatchingPassword().equals(client.getPassword());
        }
        return false;

    }
}
