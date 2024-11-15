package com.example.E_commerce_v2.mapper;

import com.example.E_commerce_v2.dto.basket.BasketDto;
import com.example.E_commerce_v2.entity.Basket;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BasketMapper {
    BasketDto toDto(Basket basket);

}