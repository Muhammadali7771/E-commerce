package com.example.E_commerce_v2.service;

import com.example.E_commerce_v2.dto.category.CategoryCreateDto;
import com.example.E_commerce_v2.dto.category.CategoryDto;
import com.example.E_commerce_v2.dto.category.CategoryUpdateDto;
import com.example.E_commerce_v2.entity.Category;
import com.example.E_commerce_v2.mapper.CategoryMapper;
import com.example.E_commerce_v2.exception.ResourceNotFoundException;
import com.example.E_commerce_v2.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public CategoryDto save(CategoryCreateDto dto) {
        Category category = categoryMapper.toEntity(dto);
        categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto getById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return categoryMapper.toDto(category);
    }

    @Override
    public void update(Integer id, CategoryUpdateDto dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        categoryMapper.partialUpdate(dto, category);
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toDtoList(categories);
    }
}
