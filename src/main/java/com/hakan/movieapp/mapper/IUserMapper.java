package com.hakan.movieapp.mapper;

import com.hakan.movieapp.dto.request.UserLoginRequestDto;
import com.hakan.movieapp.dto.request.UserRegisterRequestDto;
import com.hakan.movieapp.dto.response.UserLoginResponseDto;
import com.hakan.movieapp.dto.response.UserRegisterResponseDto;
import com.hakan.movieapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE) //IGNORE boş geçilen fieldları ya da uyuşmayan yerleri görmezden gelmesini sağlıyor yani hata vermemesi için kullanıyoruz
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User dtoToUser(UserRegisterRequestDto dto); //dto yu User a dönüştürüyoruz

    UserRegisterResponseDto userToDto(User user);

    UserLoginResponseDto userToLoginDto(User user);

}
