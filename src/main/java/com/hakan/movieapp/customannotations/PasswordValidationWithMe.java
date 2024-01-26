package com.hakan.movieapp.customannotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy =  PasswordValidatorManager.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordValidationWithMe {
    String message() default "Lütfen parolayı düzgün giriniz";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
