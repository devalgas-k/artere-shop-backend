package com.artere.shop.cart.domain.port.in;

import com.artere.shop.cart.domain.model.Cart;
import com.artere.shop.cart.domain.model.CartId;
import com.artere.shop.catalogue.domain.model.ProductId;

public interface CartUseCase {
    Cart createCart();
    Cart getCart(CartId cartId);
    Cart addProductToCart(CartId cartId, ProductId productId, int quantity);
    Cart removeProductFromCart(CartId cartId, ProductId productId);
}
