package com.hakan.movieapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(MovieAppException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleManagerException(MovieAppException ex){
        HttpStatus httpStatus=ex.getErrorType().getHttpStatus();
        return new ResponseEntity(createError(ex),httpStatus);
    }

    //Farklı hataları yakalamak içinde ArithmeticException hatası için yeni bir handle metodu yazıyoruz.
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleManagerException(ArithmeticException ex){
        HttpStatus httpStatus=HttpStatus.BAD_REQUEST;
        return new ResponseEntity(createError(ex, httpStatus.value()),httpStatus);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleManagerException(Exception ex){
        HttpStatus httpStatus=HttpStatus.BAD_REQUEST;
        return new ResponseEntity(createError(ex, httpStatus.value()),httpStatus);
    }

    private ErrorMessage createError(MovieAppException ex){
        return ErrorMessage.builder()
                .code(ex.getErrorType().getCode())
                .message(ex.getMessage())
                .build();
    }

    private ErrorMessage createError(Exception ex,int value){
        return ErrorMessage.builder()
                .code(value)
                .message(ex.getMessage())
                .build();
    }


}
