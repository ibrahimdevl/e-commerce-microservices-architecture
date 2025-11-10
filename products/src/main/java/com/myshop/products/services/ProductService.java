package com.myshop.products.services;

import com.myshop.products.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> findAll();
    ProductDto findById(final Integer productId);
    ProductDto save(final ProductDto productDto);
    ProductDto update(final ProductDto productDto);
    ProductDto update(final Integer productId, final ProductDto productDto);
    void deleteById(final Integer productId);
}
