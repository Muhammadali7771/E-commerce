package com.example.E_commerce_v2.service;

import com.example.E_commerce_v2.config.security.JwtTokenUtil;
import com.example.E_commerce_v2.dto.AuthUser.AuthUserCreateDto;
import com.example.E_commerce_v2.dto.AuthUser.AuthenticationRequest;
import com.example.E_commerce_v2.dto.TokenResponse;
import com.example.E_commerce_v2.entity.AuthUser;
import com.example.E_commerce_v2.entity.Basket;
import com.example.E_commerce_v2.mapper.AuthUserMapper;
import com.example.E_commerce_v2.enums.Role;
import com.example.E_commerce_v2.repository.AuthUserRepository;
import com.example.E_commerce_v2.exception.UsernameAlreadyExists;
import com.example.E_commerce_v2.repository.BasketRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserServiceImpl implements AuthUserService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthUserRepository authUserRepository;
    private final AuthUserMapper authUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final BasketRepository basketRepository;

    public AuthUserServiceImpl(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, AuthUserRepository authUserRepository, AuthUserMapper authUserMapper, PasswordEncoder passwordEncoder, BasketRepository basketRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authUserRepository = authUserRepository;
        this.authUserMapper = authUserMapper;
        this.passwordEncoder = passwordEncoder;
        this.basketRepository = basketRepository;
    }

    @Override
    public TokenResponse login(AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        authenticationManager.authenticate(authenticationToken);
        String token = jwtTokenUtil.generateToken(username);
        return new TokenResponse(token);
    }

    @Override
    public TokenResponse save(AuthUserCreateDto dto) {
        if (authUserRepository.findByUsername(dto.getUsername()).isPresent()){
            throw new UsernameAlreadyExists("Username already exists");
        }
        AuthUser authUser = authUserMapper.toEntity(dto);
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        authUser.setRole(Role.USER);
        authUserRepository.save(authUser);

        Basket basket = new Basket();
        basket.setAuthUser(authUser);
        basketRepository.save(basket);

        String token = jwtTokenUtil.generateToken(dto.getUsername());
        return new TokenResponse(token);
    }
}
