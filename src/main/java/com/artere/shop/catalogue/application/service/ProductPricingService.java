package com.artere.shop.catalogue.application.service;

import com.artere.shop.catalogue.api.ProductPricingApi;
import com.artere.shop.catalogue.domain.model.ProductId;
import com.artere.shop.catalogue.domain.port.out.ProductRepositoryPort;
import com.artere.shop.shared.domain.model.Money;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductPricingService implements ProductPricingApi {

    private final ProductRepositoryPort productRepository;

    public ProductPricingService(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Money> getPrice(ProductId productId) {
        return productRepository.findById(productId)
                .map(product -> product.getPrice());
    }
}
