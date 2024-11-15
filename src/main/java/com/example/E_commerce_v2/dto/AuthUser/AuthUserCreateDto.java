package com.example.E_commerce_v2.dto.AuthUser;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserCreateDto {
   @NotBlank
   private String username;
   @NotBlank
   private String password;
}
