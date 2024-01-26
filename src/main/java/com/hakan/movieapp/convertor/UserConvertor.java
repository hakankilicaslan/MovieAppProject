package com.hakan.movieapp.convertor;

import com.hakan.movieapp.dto.request.UserRegisterRequestDto;
import com.hakan.movieapp.entity.User;

public class UserConvertor {

    //Mapper yerine Convertor kullanarak aynı işlemi kendimizde yapabiliriz. Mapper tip dömnüşümü yapıyor.
    //Metodumuza parametre olarak dto alıyoruz ve geriye user dönüyoruz. builder kullanarak dtodaki değişken değerlerini user içine aktarıyoruz.
    public static User dtoToUser(UserRegisterRequestDto dto){
        if(dto == null){
            return null;
        }
        User user = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
        return user;
    }
}
