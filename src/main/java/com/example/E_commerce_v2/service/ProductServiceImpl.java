package com.example.E_commerce_v2.service;

import com.example.E_commerce_v2.dto.product.ProductCreateDto;
import com.example.E_commerce_v2.dto.product.ProductDto;
import com.example.E_commerce_v2.dto.product.ProductUpdateDto;
import com.example.E_commerce_v2.entity.Category;
import com.example.E_commerce_v2.entity.Product;
import com.example.E_commerce_v2.exception.ResourceNotFoundException;
import com.example.E_commerce_v2.mapper.ProductMapper;
import com.example.E_commerce_v2.repository.CategoryRepository;
import com.example.E_commerce_v2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(CategoryRepository categoryRepository, ProductMapper productMapper,
                              ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto save(ProductCreateDto productCreateDto, Integer categoryId) {
        Product product = productMapper.toEntity(productCreateDto);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        product.setCategory(category);
        productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public void update(Integer id, ProductUpdateDto productUpdateDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Product productUpdated = productMapper.partialUpdate(productUpdateDto, product);
        productRepository.save(productUpdated);
    }

    @Override
    public void delete(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto get(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();
        return productMapper.toDtoList(products);
    }

    @Override
    public List<ProductDto> getProductsByCategoryId(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        List<Product> products = category.getProducts();
        return productMapper.toDtoList(products);
    }
}
