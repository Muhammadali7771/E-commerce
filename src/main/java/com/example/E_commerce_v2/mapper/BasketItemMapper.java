package com.example.E_commerce_v2.mapper;

import com.example.E_commerce_v2.dto.basket.BasketDto;
import com.example.E_commerce_v2.entity.BasketItem;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BasketItemMapper {

    BasketDto toDto(BasketItem basketItem);

    List<BasketDto> toDtoList(List<BasketItem> items);
}