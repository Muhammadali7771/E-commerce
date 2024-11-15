package com.example.E_commerce_v2.mapper;

import com.example.E_commerce_v2.dto.category.CategoryCreateDto;
import com.example.E_commerce_v2.dto.category.CategoryDto;
import com.example.E_commerce_v2.dto.category.CategoryUpdateDto;
import com.example.E_commerce_v2.entity.Category;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    Category toEntity(CategoryCreateDto categoryCreateDto);
    CategoryDto toDto(Category category);
    List<CategoryDto> toDtoList(List<Category> categories);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryUpdateDto categoryUpdateDto, @MappingTarget Category category);
}