package com.myshop.products.controllers;

import com.myshop.products.dto.DtoCollectionResponse;
import com.myshop.products.dto.ProductDto;
import com.myshop.products.services.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
@RequiredArgsConstructor
public class ProductsController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<DtoCollectionResponse<ProductDto>> findAll() {
        log.info("*** ProductDto List, controller; fetch all products *");
        return ResponseEntity.ok(new DtoCollectionResponse<>(this.productService.findAll()));
    }

    @PostMapping
    public ResponseEntity<ProductDto> save(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final ProductDto productDto) {
        log.info("*** ProductDto, controller; save product *");
        return ResponseEntity.ok(this.productService.save(productDto));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> findById(
            @PathVariable("productId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String productId) {
        log.info("*** ProductDto, controller; fetch product by id *");
        return ResponseEntity.ok(this.productService.findById(Integer.parseInt(productId)));
    }

    @PutMapping
    public ResponseEntity<ProductDto> update(
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final ProductDto productDto) {
        log.info("*** ProductDto, controller; update product *");
        return ResponseEntity.ok(this.productService.update(productDto));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> update(
            @PathVariable("productId")
            @NotBlank(message = "Input must not be blank!")
            @Valid final String productId,
            @RequestBody
            @NotNull(message = "Input must not be NULL!")
            @Valid final ProductDto productDto) {
        log.info("*** ProductDto, controller; update product with productId *");
        return ResponseEntity.ok(this.productService.update(Integer.parseInt(productId), productDto));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("productId") final String productId) {
        log.info("*** Boolean, resource; delete product by id *");
        this.productService.deleteById(Integer.parseInt(productId));
        return ResponseEntity.ok(true);
    }

}
