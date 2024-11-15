package com.example.E_commerce_v2.controller;

import com.example.E_commerce_v2.dto.BaseResponse;
import com.example.E_commerce_v2.dto.basket.BasketDto;
import com.example.E_commerce_v2.dto.basket_item.BasketItemRequestDto;
import com.example.E_commerce_v2.service.BasketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BasketController {
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping("/products/add-to-cart/{productId}")
    public ResponseEntity<Void> addProductToCard(@RequestBody BasketItemRequestDto basketItemRequestDto,
                                                 @PathVariable Integer productId){
        basketService.addProductToCart(productId, basketItemRequestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/my-basket")
    public BaseResponse<BasketDto> getMyBasket(){
        BasketDto myBasket = basketService.getMyBasket();
        return new BaseResponse<>(myBasket);
    }
    @GetMapping("/remove-from-cart/{productId}")
    public ResponseEntity<Void> deleteProductFromCart(@PathVariable Integer productId){
        basketService.deleteProductFromBasket(productId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/update-cart/{productId}/{quantity}")
    public ResponseEntity<Void> updateCart(@PathVariable Integer productId, @PathVariable Integer quantity){
        basketService.updateCart(productId, quantity);
        return ResponseEntity.noContent().build();
    }
}
