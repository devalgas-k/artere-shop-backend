package com.artere.shop.cart.domain.port.out;

import com.artere.shop.cart.domain.model.Cart;
import com.artere.shop.cart.domain.model.CartId;

import java.util.Optional;

public interface CartRepositoryPort {
    Cart save(Cart cart);
    Optional<Cart> findById(CartId id);
}
