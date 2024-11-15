package com.example.E_commerce_v2.dto.basket;

import com.example.E_commerce_v2.dto.basket_item.BasketItemDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BasketDto {
    private List<BasketItemDto> items;
    private double totalPrice;
}
