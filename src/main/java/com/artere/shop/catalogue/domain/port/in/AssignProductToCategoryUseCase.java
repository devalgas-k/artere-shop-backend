package com.artere.shop.catalogue.domain.port.in;

import com.artere.shop.catalogue.domain.model.CategoryId;
import com.artere.shop.catalogue.domain.model.ProductId;

public interface AssignProductToCategoryUseCase {
    void assign(ProductId productId, CategoryId categoryId);
}
