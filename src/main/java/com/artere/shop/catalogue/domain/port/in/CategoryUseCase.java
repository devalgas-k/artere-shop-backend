package com.artere.shop.catalogue.domain.port.in;

import com.artere.shop.catalogue.domain.model.Category;
import com.artere.shop.catalogue.domain.model.CategoryId;
import com.artere.shop.catalogue.domain.model.ProductId;

public interface CategoryUseCase {
    Category createCategory(String name, String description, CategoryId parentId);
    void assignProductToCategory(CategoryId categoryId, ProductId productId);
    Category getCategory(CategoryId categoryId);
}
