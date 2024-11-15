package com.example.E_commerce_v2.controller;

import com.example.E_commerce_v2.dto.AuthUser.AuthUserCreateDto;
import com.example.E_commerce_v2.dto.AuthUser.AuthenticationRequest;
import com.example.E_commerce_v2.dto.BaseResponse;
import com.example.E_commerce_v2.dto.TokenResponse;
import com.example.E_commerce_v2.service.AuthUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthUserService authUserService;

    public AuthController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<TokenResponse> generateToken(@RequestBody AuthenticationRequest request){
        TokenResponse tokenResponse = authUserService.login(request);
        return new BaseResponse<>(tokenResponse);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<TokenResponse> register(@RequestBody AuthUserCreateDto dto){
        TokenResponse tokenResponse = authUserService.save(dto);
        return new BaseResponse<>(tokenResponse);
    }
}
