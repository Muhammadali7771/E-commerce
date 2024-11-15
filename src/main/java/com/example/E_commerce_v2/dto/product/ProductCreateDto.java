package com.example.E_commerce_v2.dto.product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDto {
    @NotBlank(message = "name can not be blank")
    private String name;
    @NotBlank(message = "description can not be blank")
    private String description;
    @NotNull(message = "quantity can not be null")
    @Min(value =  0, message = "quantity can not be negative")
    private Integer quantity;
    @NotNull(message = "price can not be null")
    @DecimalMin(value = "0", message = "price can not be negative")
    private Double price;
}
