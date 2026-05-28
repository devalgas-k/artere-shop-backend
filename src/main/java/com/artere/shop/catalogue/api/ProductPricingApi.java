package com.artere.shop.catalogue.api;

import com.artere.shop.catalogue.domain.model.ProductId;
import com.artere.shop.shared.domain.model.Money;

import java.util.Optional;

public interface ProductPricingApi {
    Optional<Money> getPrice(ProductId productId);
}
