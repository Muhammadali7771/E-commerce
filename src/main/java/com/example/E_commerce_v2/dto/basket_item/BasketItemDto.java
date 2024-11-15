package com.example.E_commerce_v2.dto.basket_item;

import com.example.E_commerce_v2.dto.product.ProductDto;
import com.example.E_commerce_v2.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketItemDto {
    private ProductDto product;
    private Integer quantity;
    private Double price;

}
