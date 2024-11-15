package com.example.E_commerce_v2.service;

import com.example.E_commerce_v2.config.security.SessionUser;
import com.example.E_commerce_v2.dto.basket.BasketDto;
import com.example.E_commerce_v2.dto.basket_item.BasketItemRequestDto;
import com.example.E_commerce_v2.entity.Basket;
import com.example.E_commerce_v2.entity.BasketItem;
import com.example.E_commerce_v2.entity.Product;
import com.example.E_commerce_v2.exception.ResourceNotFoundException;
import com.example.E_commerce_v2.mapper.BasketMapper;
import com.example.E_commerce_v2.repository.BasketItemRepository;
import com.example.E_commerce_v2.repository.BasketRepository;
import com.example.E_commerce_v2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasketService {
    private final ProductRepository productRepository;
    private final SessionUser sessionUser;
    private final BasketItemRepository basketItemRepository;
    private final BasketRepository basketRepository;
    private final BasketMapper basketMapper;

    public BasketService(ProductRepository productRepository, SessionUser sessionUser, BasketItemRepository basketItemRepository, BasketRepository basketRepository, BasketMapper basketMapper) {
        this.productRepository = productRepository;
        this.sessionUser = sessionUser;
        this.basketItemRepository = basketItemRepository;
        this.basketRepository = basketRepository;
        this.basketMapper = basketMapper;
    }

    public void addProductToCart(Integer productId, BasketItemRequestDto basketItemRequestDto){
        Integer quantity = basketItemRequestDto.getQuantity();
        Integer currentUserId = sessionUser.getId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        if (product.getQuantity() == 0){
            throw new RuntimeException("Product is not available");
        }
        if (product.getQuantity() < quantity){
            throw new RuntimeException("Please make a request less than " + product.getQuantity());
        }

        Basket basket = basketRepository.getBasketByAuthUserId(currentUserId);
        BasketItem basketItem = new BasketItem();
        basketItem.setProduct(product);
        basketItem.setQuantity(quantity);
        basketItem.setBasket(basket);
        double price = product.getPrice() * quantity;
        basketItem.setPrice(price);
        basket.setTotalPrice(basket.getTotalPrice() + price);
        basketItemRepository.save(basketItem);

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }

    public BasketDto getMyBasket(){
        Integer currentUserId = sessionUser.getId();
        Basket basket = basketRepository.getBasketByAuthUserId(currentUserId);
        return basketMapper.toDto(basket);
    }

    public void deleteProductFromBasket(Integer productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product is not found"));
        Integer currentUserId = sessionUser.getId();
        Basket basket = basketRepository.getBasketByAuthUserId(currentUserId);
        BasketItem basketItem = basketItemRepository.getBasketItemByBasketIdAndProductId(basket.getId(), productId)
                .orElseThrow(() -> new ResourceNotFoundException("Basket Item not found"));
        basket.setTotalPrice(basket.getTotalPrice() - basketItem.getPrice());
        basketRepository.save(basket);
        product.setQuantity(product.getQuantity() + basketItem.getQuantity());
        basketItemRepository.deleteBasketItemByBasketIdAndProductId(basket.getId(), productId);
    }

    public void updateCart(Integer productId, Integer quantity){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Integer currentUserId = sessionUser.getId();
        Basket basket = basketRepository.getBasketByAuthUserId(currentUserId);
        BasketItem basketItem = basketItemRepository.getBasketItemByBasketIdAndProductId(basket.getId(), productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found in basket"));
        double cartPrice = basket.getTotalPrice() - basketItem.getPrice();
        Integer oldQuantity = basketItem.getQuantity();
        basketItem.setQuantity(quantity);
        basketItem.setPrice(product.getPrice() * quantity);
        basket.setTotalPrice(cartPrice + basketItem.getPrice());
        basketRepository.save(basket);

        product.setQuantity(product.getQuantity() + oldQuantity - quantity);
        productRepository.save(product);
        basketItemRepository.save(basketItem);
    }
}
