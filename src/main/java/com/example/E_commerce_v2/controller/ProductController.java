package com.example.E_commerce_v2.controller;

import com.example.E_commerce_v2.dto.BaseResponse;
import com.example.E_commerce_v2.dto.product.ProductCreateDto;
import com.example.E_commerce_v2.dto.product.ProductDto;
import com.example.E_commerce_v2.dto.product.ProductUpdateDto;
import com.example.E_commerce_v2.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/categories/{categoryId}/product")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<ProductDto> addProduct(@PathVariable Integer categoryId,
                                               @Valid @RequestBody ProductCreateDto dto){
        ProductDto productDto = productService.save(dto, categoryId);
        return new BaseResponse<>(productDto);
    }

    @GetMapping("/product")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<ProductDto>> getProducts(){
        List<ProductDto> products = productService.getAll();
        return new BaseResponse<>(products);
    }

    @GetMapping("/product/{id}")
    public BaseResponse<ProductDto> getById(@PathVariable Integer id){
        ProductDto productDto = productService.get(id);
        return new BaseResponse<>(productDto);
    }

    @PutMapping("/product/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> update(@PathVariable Integer id,
                                       @Valid @RequestBody ProductUpdateDto dto){
        productService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/product/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/categories/{categoryId}/products")
    public BaseResponse<List<ProductDto>> getAllProductsByCategoryId(@PathVariable Integer categoryId){
        List<ProductDto> products = productService.getProductsByCategoryId(categoryId);
        return new BaseResponse<>(products);
    }
}
