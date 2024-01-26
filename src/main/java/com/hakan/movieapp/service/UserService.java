package com.hakan.movieapp.service;

import com.hakan.movieapp.convertor.UserConvertor;
import com.hakan.movieapp.dto.request.UserLoginRequestDto;
import com.hakan.movieapp.dto.request.UserRegisterRequestDto;
import com.hakan.movieapp.dto.response.UserLoginResponseDto;
import com.hakan.movieapp.dto.response.UserRegisterResponseDto;
import com.hakan.movieapp.entity.User;
import com.hakan.movieapp.exception.ErrorType;
import com.hakan.movieapp.exception.MovieAppException;
import com.hakan.movieapp.mapper.IUserMapper;
import com.hakan.movieapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public void saveAll(List<User> users){
        userRepository.saveAll(users);
    }

    public List<User> findAllByOrderByName() {
        return userRepository.findAllByOrderByName();
    }
    public List<User> findAllByNameLike(String name) {
        String newWord = "%"+name+"%";
        return userRepository.findAllByNameLike(newWord);
    }
    public List<User> findAllByEmailContainingIgnoreCase(String email) {
        return userRepository.findAllByEmailContainingIgnoreCase(email);
    }
    public List<User> findAllByEmailEndsWith(String word) {
        return userRepository.findAllByEmailEndsWith(word);
    }
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email,password);
    }
    public Boolean existsByEmailAndPassword(String email,String password) {
        return userRepository.existsByEmailAndPassword(email,password);
    }
    public List<User> passwordLongerThanNativeQuery(Integer value) {
        return userRepository.passwordLongerThanNativeQuery(value);
    }
    public List<User> passwordLongerThanJPQL(Integer value) {
        return userRepository.passwordLongerThanJPQL(value);
    }

    /*public User register(String email, String name, String password, String rePassword) { //dto ya çevirmeden önceki hali
        User user = null;
        if(!userRepository.existsByEmail(email)){
            if(password.equals(rePassword)){
                user = User.builder()
                        .name(name)
                        .email(email)
                        .password(password)
                        .build();
                userRepository.save(user);
            } else{
                throw new RuntimeException("Girilen şifreler uyuşmuyor.");
            }
        } else{
            throw new RuntimeException("Mail adresi başka bir kullanıcı tarafından kullanılıyor.");
        }
        return user;
    }*/

    /*public User register(UserRegisterRequestDto dto) {
        User user = null;
        if(!userRepository.existsByEmail(dto.getEmail())){
            if(dto.getPassword().equals(dto.getRePassword())){

                user = IUserMapper.INSTANCE.dtoToUser(dto);
                //user = UserConvertor.dtoToUser(dto); //Mapper yerine Convertor kullanarak kendimiz yazmak istersek bunu kullanıyoruz.

                userRepository.save(user);
            }else {
                throw new RuntimeException("Şifrele uyuşmuor");
            }
        }else {
            throw new RuntimeException("Mail adresi zaten kullanılıyor.");
        }
        return user;
    }*/

    public UserRegisterResponseDto register(UserRegisterRequestDto dto) {
        User user = null;
        if(!userRepository.existsByEmail(dto.getEmail())){
            if(dto.getPassword().equals(dto.getRePassword())){

                user = IUserMapper.INSTANCE.dtoToUser(dto);
                //user = UserConvertor.dtoToUser(dto); //Mapper yerine Convertor kullanarak kendimiz yazmak istersek bunu kullanıyoruz.

                userRepository.save(user);
            }else {
                throw new RuntimeException("Şifrele uyuşmuor");
            }
        }else {
            throw new RuntimeException("Mail adresi zaten kullanılıyor.");
        }
        return IUserMapper.INSTANCE.userToDto(user);
    }

    public UserLoginResponseDto login(UserLoginRequestDto dto) {
       Optional<User> optionalUser = userRepository.findByEmailAndPassword(dto.getEmail(),dto.getPassword());
        if(optionalUser.isPresent()){
            return IUserMapper.INSTANCE.userToLoginDto(optionalUser.get());
        }else {
            //throw new RuntimeException("Kullanıcı adı veya şifre hatalı..."); Artık bunu değil MovieAppException göndercez.
            throw new MovieAppException(ErrorType.USER_NOT_FOUND);
        }
    }




}
