package com.example.E_commerce_v2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AuthUser authUser;
    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
    private List<BasketItem> items;
    private double totalPrice;
}
