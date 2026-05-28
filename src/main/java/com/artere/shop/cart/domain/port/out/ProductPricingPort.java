package com.artere.shop.cart.domain.port.out;

import com.artere.shop.catalogue.domain.model.ProductId;
import com.artere.shop.shared.domain.model.Money;

import java.util.Optional;

public interface ProductPricingPort {
    Optional<Money> getProductPrice(ProductId productId);
}
