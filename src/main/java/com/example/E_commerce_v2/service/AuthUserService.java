package com.example.E_commerce_v2.service;

import com.example.E_commerce_v2.dto.AuthUser.AuthUserCreateDto;
import com.example.E_commerce_v2.dto.AuthUser.AuthenticationRequest;
import com.example.E_commerce_v2.dto.TokenResponse;

public interface AuthUserService {
    TokenResponse login(AuthenticationRequest authenticationRequest);
    TokenResponse save(AuthUserCreateDto dto);
}
