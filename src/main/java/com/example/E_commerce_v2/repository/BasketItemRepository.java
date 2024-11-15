package com.example.E_commerce_v2.repository;

import com.example.E_commerce_v2.entity.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BasketItemRepository extends JpaRepository<BasketItem, Integer> {
    @Transactional
    @Modifying
    void deleteBasketItemByBasketIdAndProductId(Integer basketId, Integer productId);

    Optional<BasketItem> getBasketItemByBasketIdAndProductId(Integer basketId, Integer productId);
}
