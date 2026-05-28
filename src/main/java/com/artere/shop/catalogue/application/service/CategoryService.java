package com.artere.shop.catalogue.application.service;

import com.artere.shop.catalogue.domain.model.Category;
import com.artere.shop.catalogue.domain.model.CategoryId;
import com.artere.shop.catalogue.domain.model.Product;
import com.artere.shop.catalogue.domain.model.ProductId;
import com.artere.shop.catalogue.domain.port.in.CategoryUseCase;
import com.artere.shop.catalogue.domain.port.out.CategoryRepositoryPort;
import com.artere.shop.catalogue.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryService implements CategoryUseCase {

    private final CategoryRepositoryPort categoryRepository;
    private final ProductRepositoryPort productRepository;

    public CategoryService(CategoryRepositoryPort categoryRepository, ProductRepositoryPort productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Category createCategory(String name, String description, CategoryId parentId) {
        if (parentId != null) {
            // Verify parent exists
            categoryRepository.findById(parentId)
                    .orElseThrow(() -> new IllegalArgumentException("Parent category not found: " + parentId.value()));
        }
        
        Category category = new Category(new CategoryId(null), name, description, parentId);
        return categoryRepository.save(category);
    }

    @Override
    public void assignProductToCategory(CategoryId categoryId, ProductId productId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + categoryId.value()));
                
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId.value()));
                
        product.assignToCategory(categoryId);
        productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategory(CategoryId categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + categoryId.value()));
    }
}
