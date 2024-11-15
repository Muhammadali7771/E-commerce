package com.example.E_commerce_v2.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCreateDto {
    @NotBlank(message = "name can not be blank")
    public String name;
    @NotBlank(message = "description can not be blank")
    public String description;
}
