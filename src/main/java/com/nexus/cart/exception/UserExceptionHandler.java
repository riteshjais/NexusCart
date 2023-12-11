package com.nexus.cart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(value = EntityExistsException.class)
    public ResponseEntity<Object> handleEntityExistsException(EntityExistsException entityExistsException){
        UserExceptionMessage userExceptionMessage=new UserExceptionMessage(entityExistsException.getMessage(),entityExistsException.getCause(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userExceptionMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException){
        UserExceptionMessage userExceptionMessage=new UserExceptionMessage(entityNotFoundException.getMessage(),entityNotFoundException.getCause(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userExceptionMessage,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException e){
        UserExceptionMessage userExceptionMessage=new UserExceptionMessage(e.getMessage(), e.getCause(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userExceptionMessage,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception e){
        UserExceptionMessage userExceptionMessage=new UserExceptionMessage(e.getMessage(), e.getCause(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userExceptionMessage,HttpStatus.NOT_FOUND);
    }




}