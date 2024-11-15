package com.example.E_commerce_v2.controller;

import com.example.E_commerce_v2.dto.BaseResponse;
import com.example.E_commerce_v2.dto.category.CategoryCreateDto;
import com.example.E_commerce_v2.dto.category.CategoryDto;
import com.example.E_commerce_v2.dto.category.CategoryUpdateDto;
import com.example.E_commerce_v2.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<CategoryDto>> getAll(){
        List<CategoryDto> categories = categoryService.findAll();
        return new BaseResponse<>(categories);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<CategoryDto> getById(@PathVariable Integer id){
        CategoryDto category = categoryService.getById(id);
        return new BaseResponse<>(category);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<CategoryDto> save(@Valid @RequestBody CategoryCreateDto categoryCreateDto){
        CategoryDto dto = categoryService.save(categoryCreateDto);
        return new BaseResponse<>(dto);
    }
    
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@RequestBody CategoryUpdateDto dto,
                                  @PathVariable Integer id){
        categoryService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
