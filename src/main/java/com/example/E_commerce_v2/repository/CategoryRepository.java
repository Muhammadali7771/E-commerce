package com.example.E_commerce_v2.repository;

import com.example.E_commerce_v2.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}