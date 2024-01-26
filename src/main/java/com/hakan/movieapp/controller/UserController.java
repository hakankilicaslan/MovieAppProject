package com.hakan.movieapp.controller;

import com.hakan.movieapp.dto.request.UserLoginRequestDto;
import com.hakan.movieapp.dto.request.UserRegisterRequestDto;
import com.hakan.movieapp.dto.response.UserLoginResponseDto;
import com.hakan.movieapp.dto.response.UserRegisterResponseDto;
import com.hakan.movieapp.entity.User;
import com.hakan.movieapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /*@PostMapping("/register") //dto ya çevirmeden önceki hali
    public ResponseEntity<User> register(String email,String name,String password,String rePassword){
        return ResponseEntity.ok(userService.register(email,name,password,rePassword));
    }*/

    /*@PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegisterRequestDto dto){ //@RequestBody anotasyonu verileri body den almamızı sağlıyor ve serilize işlemi yapıyor bu şekilde yapmak daha mantıklı. Bunu kullanınca url kısmında verileri göstermiyror gizlilik sağlıyor.
        return ResponseEntity.ok(userService.register(dto));
    }*/

    //Register metodunda her şeyi değilde id,name,email,genres sadece bunları döndürmemizi istediler diyelim.
    //Bundan dolayı geriye UserRegisterResponseDto dönüyoruz istenen parametreleri içine veriyoruz.
    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody @Validated UserRegisterRequestDto dto){ //@Validated koymamızın nedeni build gradle içine validation import ettik ve bunu anotasyonu koyduktan sonra @Email anotasyonu koyduğumuz için maili girerken @ şeklinde yazılmayınca hata fırlatacak.
        return ResponseEntity.ok(userService.register(dto));
        //return new ResponseEntity(userService.register(dto), HttpStatus.CREATED);
    }

    //Login metodu dto alsın geriye dto dönsün -> id
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto dto){
        //System.out.println(0/100 + 100/0);  öylesine bir hata meydana getirmek için kullandık AritmethicException yakalasın diye koyduk
        return ResponseEntity.ok(userService.login(dto));
        //return ResponseEntity.status(HttpStatus.FOUND).body(userService.login(dto));
    }

    @GetMapping("/findAllByOrderName")
    public ResponseEntity<List<User>> findAllByOrderByName(){
        return ResponseEntity.ok(userService.findAllByOrderByName());
    }

    @GetMapping("/findAllByNameLike")
    public ResponseEntity<List<User>> findAllByNameLike(String name){
        return ResponseEntity.ok(userService.findAllByNameLike(name));
    }

    @GetMapping("/findAllByEmailContainingIgnoreCase")
    public ResponseEntity<List<User>> findAllByEmailContainingIgnoreCase(String email){
        return ResponseEntity.ok(userService.findAllByEmailContainingIgnoreCase(email));
    }

    @GetMapping("/findAllByEmailEndsWith")
    public ResponseEntity<List<User>> findAllByEmailEndsWith(String word){
        return ResponseEntity.ok(userService.findAllByEmailEndsWith(word));
    }

    @GetMapping("/findByEmailAndPassword")
    public ResponseEntity<User> findByEmailAndPassword(String email, String password){
        return ResponseEntity.of(userService.findByEmailAndPassword(email,password));
    }

    @GetMapping("/existsByEmailAndPassword")
    public ResponseEntity<Boolean> existsByEmailAndPassword(String email, String password){
        return ResponseEntity.ok(userService.existsByEmailAndPassword(email,password));
    }

    @GetMapping("/passwordLongerThanNativeQuery")
    public ResponseEntity<List<User>> passwordLongerThanNativeQuery(Integer value){
        return ResponseEntity.ok(userService.passwordLongerThanNativeQuery(value));
    }

    @GetMapping("/passwordLongerThanJPQL")
    public ResponseEntity<List<User>> passwordLongerThanJPQL(Integer value){
        return ResponseEntity.ok(userService.passwordLongerThanJPQL(value));
    }

}
