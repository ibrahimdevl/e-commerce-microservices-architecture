package com.myshop.products.controllers;

import com.myshop.products.dto.CategoryDto;
import com.myshop.products.dto.DtoCollectionResponse;
import com.myshop.products.services.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/categories")
@Slf4j
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService ;

    @GetMapping
    public ResponseEntity<DtoCollectionResponse<CategoryDto>> findAll() {
        log.info("*** CategoryDto List, controller; fetch all categories *");
        return ResponseEntity.ok(new DtoCollectionResponse<>(this.categoryService.findAll()));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto>findById(
            @PathVariable("categoryId")
            @NotBlank(message = "Input must not be blank")
            @Valid final String categoryId
    ) {
        log.info("*** CategoryDto List, controller; fetch all categories *");
        return ResponseEntity.ok(categoryService.findById(Integer.parseInt(categoryId)));
    }


    @PostMapping
    public ResponseEntity<CategoryDto> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL")
            @Valid final CategoryDto categoryDto) {
        log.info("*** CategoryDto, resource; save category *");
        return ResponseEntity.ok(this.categoryService.save(categoryDto));
    }

}
