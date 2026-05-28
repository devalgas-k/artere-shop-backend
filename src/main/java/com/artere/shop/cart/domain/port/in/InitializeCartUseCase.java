package com.artere.shop.cart.domain.port.in;

import com.artere.shop.cart.domain.model.CartId;

public interface InitializeCartUseCase {
    CartId initializeCart();
}
