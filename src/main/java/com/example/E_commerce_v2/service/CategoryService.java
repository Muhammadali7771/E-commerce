package com.example.E_commerce_v2.service;

import com.example.E_commerce_v2.dto.category.CategoryCreateDto;
import com.example.E_commerce_v2.dto.category.CategoryDto;
import com.example.E_commerce_v2.dto.category.CategoryUpdateDto;

import java.util.List;

public interface CategoryService {
   CategoryDto save(CategoryCreateDto dto);
   CategoryDto getById(Integer id);
   void update(Integer id, CategoryUpdateDto dto);
   void deleteById(Integer id);
   List<CategoryDto> findAll();

}
