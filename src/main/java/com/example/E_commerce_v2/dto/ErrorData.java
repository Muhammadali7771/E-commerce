package com.example.E_commerce_v2.dto;

public record ErrorData(String message, String path, Object params) {
    public ErrorData(String message, String path){
        this(message, path, null);
    }
}
