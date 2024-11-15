package com.example.E_commerce_v2.exception;

public class UsernameAlreadyExists extends RuntimeException{
    public UsernameAlreadyExists(String message){
        super(message);
    }
}
