package com.example.E_commerce_v2.service;

import com.example.E_commerce_v2.dto.product.ProductCreateDto;
import com.example.E_commerce_v2.dto.product.ProductDto;
import com.example.E_commerce_v2.dto.product.ProductUpdateDto;

import java.util.List;

public interface ProductService {
    ProductDto save(ProductCreateDto productCreateDto, Integer categoryId);
    void update(Integer id, ProductUpdateDto productUpdateDto);
    void delete(Integer id);
    ProductDto get(Integer id);
    List<ProductDto> getAll();
    List<ProductDto> getProductsByCategoryId(Integer categoryId);
}
