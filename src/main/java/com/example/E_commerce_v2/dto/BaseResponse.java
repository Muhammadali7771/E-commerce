package com.example.E_commerce_v2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BaseResponse<T> (T result, ErrorData error, boolean success) {
    public BaseResponse(T result){
        this(result, null, true);
    }

    public BaseResponse(ErrorData error){
        this(null, error, false);
    }
}
