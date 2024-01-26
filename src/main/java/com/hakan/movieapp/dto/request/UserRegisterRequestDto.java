package com.hakan.movieapp.dto.request;

import com.hakan.movieapp.customannotations.PasswordValidationWithMe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRegisterRequestDto {

    @Email(message = "Email adresini kontrol edin")
    private String email;
    private String name;
    private String surname;
    @PasswordValidationWithMe
    private String password;
    private String rePassword;

}
