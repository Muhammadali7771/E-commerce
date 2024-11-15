package com.example.E_commerce_v2.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private Integer quantity;
    private double price;
}
