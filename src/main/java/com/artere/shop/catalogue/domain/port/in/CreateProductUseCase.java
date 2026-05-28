package com.artere.shop.catalogue.domain.port.in;

import com.artere.shop.catalogue.domain.model.ProductId;
import com.artere.shop.shared.domain.model.Money;

public interface CreateProductUseCase {
    ProductId createProduct(String name, Money price, int stockQuantity);
}
