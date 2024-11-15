package com.example.E_commerce_v2.config;

import com.example.E_commerce_v2.dto.BaseResponse;
import com.example.E_commerce_v2.dto.ErrorData;
import com.example.E_commerce_v2.exception.ResourceNotFoundException;
import com.example.E_commerce_v2.exception.UsernameAlreadyExists;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UsernameAlreadyExists.class)
    public BaseResponse<Void> usernameAlreadyExists(UsernameAlreadyExists e, HttpServletRequest request){
        return new BaseResponse<>(new ErrorData(e.getMessage(), request.getRequestURI()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public BaseResponse<Void> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        return new BaseResponse<>(new ErrorData(e.getMessage(), request.getRequestURI()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<ErrorData> handleInvalidArgument(MethodArgumentNotValidException e, HttpServletRequest request){
        Map<String, String> map = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(
                error -> {
                    String field = error.getField();
                    String errorMessage = error.getDefaultMessage();
                    map.put(field, errorMessage);
                }
        );
        return new BaseResponse<>(new ErrorData("Not valid input", request.getRequestURI(), map));
    }

}
