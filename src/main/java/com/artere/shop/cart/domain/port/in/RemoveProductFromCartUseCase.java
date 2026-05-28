package com.artere.shop.cart.domain.port.in;

import com.artere.shop.cart.domain.model.CartId;
import com.artere.shop.catalogue.domain.model.ProductId;

public interface RemoveProductFromCartUseCase {
    void removeProduct(CartId cartId, ProductId productId);
}
