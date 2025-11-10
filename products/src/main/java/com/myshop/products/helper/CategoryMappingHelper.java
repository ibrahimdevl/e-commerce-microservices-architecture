package com.myshop.products.helper;

import com.myshop.products.dto.CategoryDto;
import com.myshop.products.entities.Category;

import java.util.Optional;

public interface CategoryMappingHelper {

    public static CategoryDto map(final Category category) {

        final var parentCategory = Optional.ofNullable(category
                .getParentCategory()).orElseGet(() -> new Category());

        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .categoryTitle(category.getCategoryTitle())
                .imageUrl(category.getImageUrl())
                .parentCategoryDto(
                        CategoryDto.builder()
                                .categoryId(parentCategory.getCategoryId())
                                .categoryTitle(parentCategory.getCategoryTitle())
                                .imageUrl(parentCategory.getImageUrl())
                                .build())
                .build();
    }

    public static Category map(final CategoryDto categoryDto) {

        final var parentCategoryDto = Optional.ofNullable(categoryDto
                .getParentCategoryDto()).orElseGet(() -> new CategoryDto());

        return Category.builder()
                .categoryId(categoryDto.getCategoryId())
                .categoryTitle(categoryDto.getCategoryTitle())
                .imageUrl(categoryDto.getImageUrl())
                .parentCategory(
                        Category.builder()
                                .categoryId(parentCategoryDto.getCategoryId())
                                .categoryTitle(parentCategoryDto.getCategoryTitle())
                                .imageUrl(parentCategoryDto.getImageUrl())
                                .build())
                .build();
    }

}
