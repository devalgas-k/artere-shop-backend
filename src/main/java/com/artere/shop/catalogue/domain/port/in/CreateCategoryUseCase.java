package com.artere.shop.catalogue.domain.port.in;

import com.artere.shop.catalogue.domain.model.CategoryId;

public interface CreateCategoryUseCase {
    CategoryId createCategory(String name, String description, CategoryId parentId);
}
