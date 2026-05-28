package com.artere.shop.cart.infrastructure.adapter.out.catalogue;

import com.artere.shop.cart.domain.port.out.ProductPricingPort;
import com.artere.shop.catalogue.api.ProductPricingApi;
import com.artere.shop.catalogue.domain.model.ProductId;
import com.artere.shop.shared.domain.model.Money;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductPricingAdapter implements ProductPricingPort {

    private final ProductPricingApi productPricingApi;

    public ProductPricingAdapter(ProductPricingApi productPricingApi) {
        this.productPricingApi = productPricingApi;
    }

    @Override
    public Optional<Money> getProductPrice(ProductId productId) {
        return productPricingApi.getPrice(productId);
    }
}
