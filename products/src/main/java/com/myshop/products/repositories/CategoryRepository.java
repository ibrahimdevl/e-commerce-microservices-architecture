package com.myshop.products.repositories;

import com.myshop.products.entities.Category;
import com.myshop.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
