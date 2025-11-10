package com.myshop.products.services.Impl;

import com.myshop.products.dto.CategoryDto;
import com.myshop.products.entities.Category;
import com.myshop.products.exception.Wrapper.CategoryNotFoundException;
import com.myshop.products.helper.CategoryMappingHelper;
import com.myshop.products.helper.ProductMappingHelper;
import com.myshop.products.repositories.CategoryRepository;
import com.myshop.products.services.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> findAll() {
        log.info("*** CategoryDto List, service; fetch all categorys *");
        return this.categoryRepository.findAll()
                .stream()
                .map(CategoryMappingHelper::map)
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public CategoryDto findById(Integer categoryId) {
            log.info("*** CategoryDto, service; fetch category by id *");
            return this.categoryRepository.findById(categoryId)
                    .map(CategoryMappingHelper::map)
                    .orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id: %d not found", categoryId)));

    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        log.info("*** CategoryDto, service; save category *");
        Category category = CategoryMappingHelper.map(categoryDto);
        category.setCreatedAt(Instant.now());
        return CategoryMappingHelper.map(this.categoryRepository
                .save(category));
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        log.info("*** CategoryDto, service; update Category *");
        return CategoryMappingHelper
                .map(this.categoryRepository
                        .save(CategoryMappingHelper
                                .map(categoryDto)));
    }

    @Override
    public CategoryDto update(Integer categoryId, CategoryDto categoryDto) {
        log.info("*** CategoryDto, service; update Category with productId *");
        return CategoryMappingHelper
                .map(this.categoryRepository
                        .save(CategoryMappingHelper
                                .map(this.findById(categoryId))));
    }

    @Override
    public void deleteById(Integer categoryId) {

        log.info("*** Void, service; delete Category by id *");
        this.categoryRepository.delete(CategoryMappingHelper
                .map(this.findById(categoryId)));

    }
}
