package com.hakan.movieapp.repository;

import com.hakan.movieapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    /*
     1 - Kullanicilari isme göre sirali getiriniz
     2 - Dışarıdan girilen deger hangi kullanicilarin isimlerinde mevcuttur.
     3 - Emaillerinin icinde belirledigimiz deger gecen kullanicilar
     4 - Emailleri belirledigimiz degere göre biten kullanicilar
     5 - Email ve passworda göre kullanici kontrolu
     6 - Passwordunun uzunlugu belirledigimiz sayidan buyuk olanlar (Query yazilacak) |
     */

    List<User> findAllByOrderByName(); //1
    List<User> findAllByNameLike(String name); //2
    List<User> findAllByEmailLike(String email); //2
    List<User> findAllByEmailContainingIgnoreCase(String email); //3
    List<User> findAllByEmailEndsWith(String email); //4
    List<User> findAllByEmailStartsWith(String email); //4
    Optional<User> findByEmailAndPassword(String email,String password); //5
    Boolean existsByEmailAndPassword(String email,String password); //5 -> Varsa true yoksa false dönecek versiyonu
    Boolean existsByEmail(String email); //5
    //Native Query
    @Query(value = "select * from tbl_user where length(password)>?1",nativeQuery = true) //İçerideki 1 parametre sayısına tekabul ediyor. Birinci parametre 1 ikinci parametre 2 olarak adlandırılıyor.
    List<User> passwordLongerThanNativeQuery(int length);
    //JPQL
    @Query("select u from User u where length(u.password) > :x")
    List<User> passwordLongerThanJPQL(@Param("x") int length);

    /*@Query("select u from User u where legth(u.password) > :x and u.name = :y") //Vereceğimiz parametreyi @Param içine yazıyoruz yukarıda query içinde de o verdiğimiz x,y gibi ifadeleri kullanıyoruz.
    List<User> passwordLongerThanJPQL(@Param("x") int length,@Param("y") String name);*/

    /**
     * Yukarıda Query yazdığımız metot isimlerini istediğimiz gibi verebiliriz.
     * Diğerlerinde olduğu gibi metot ismini belli bir kalıba göre yazmak zorunda değiliz.
     * Query olarak direkt sql kodlarını yazdığımızda metot isminin önemi kalmıyor.
     */



}
