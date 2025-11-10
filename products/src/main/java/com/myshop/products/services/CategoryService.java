package com.myshop.products.services;

import com.myshop.products.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> findAll();
    CategoryDto findById(final Integer categoryId);
    CategoryDto save(final CategoryDto categoryDto);
    CategoryDto update(final CategoryDto categoryDto);
    CategoryDto update(final Integer categoryId, final CategoryDto categoryDto);
    void deleteById(final Integer categoryId);
}
