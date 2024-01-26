package com.hakan.movieapp.customannotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidatorManager implements ConstraintValidator<PasswordValidationWithMe, String> {
    @Override
    public void initialize(PasswordValidationWithMe constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if(password.length() < 5){
            return false;
        }
        return true;
    }
}
