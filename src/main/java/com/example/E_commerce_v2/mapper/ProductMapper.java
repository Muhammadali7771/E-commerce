package com.example.E_commerce_v2.mapper;

import com.example.E_commerce_v2.dto.product.ProductCreateDto;
import com.example.E_commerce_v2.dto.product.ProductDto;
import com.example.E_commerce_v2.dto.product.ProductUpdateDto;
import com.example.E_commerce_v2.entity.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    Product toEntity(ProductCreateDto productCreateDto);

    ProductDto toDto(Product product);
    List<ProductDto> toDtoList(List<Product> products);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductUpdateDto productUpdateDto, @MappingTarget Product product);
}