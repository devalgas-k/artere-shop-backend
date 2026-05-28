package com.artere.shop.catalogue.domain.port.out;

import com.artere.shop.catalogue.domain.model.Product;
import com.artere.shop.catalogue.domain.model.ProductId;

import java.util.Optional;

public interface ProductRepositoryPort {
    Product save(Product product);
    Optional<Product> findById(ProductId id);
}
