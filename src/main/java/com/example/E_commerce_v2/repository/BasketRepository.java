package com.example.E_commerce_v2.repository;

import com.example.E_commerce_v2.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
   // @Query("select b from Basket b where b.authUser.id = ?1")
   Basket getBasketByAuthUserId(Integer id);
}
