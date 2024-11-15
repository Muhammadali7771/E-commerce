package com.example.E_commerce_v2.mapper;

import com.example.E_commerce_v2.dto.AuthUser.AuthUserCreateDto;
import com.example.E_commerce_v2.entity.AuthUser;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthUserMapper {
    AuthUser toEntity(AuthUserCreateDto authUserCreateDto);

  //  AuthUserCreateDto toDto(AuthUser authUser);

}