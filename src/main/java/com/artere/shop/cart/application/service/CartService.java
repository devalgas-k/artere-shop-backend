package com.artere.shop.cart.application.service;

import com.artere.shop.cart.domain.model.Cart;
import com.artere.shop.cart.domain.model.CartId;
import com.artere.shop.cart.domain.port.in.CartUseCase;
import com.artere.shop.cart.domain.port.out.CartRepositoryPort;
import com.artere.shop.cart.domain.port.out.ProductPricingPort;
import com.artere.shop.catalogue.domain.model.ProductId;
import com.artere.shop.shared.domain.model.Money;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartService implements CartUseCase {

    private final CartRepositoryPort cartRepository;
    private final ProductPricingPort productPricingPort;

    public CartService(CartRepositoryPort cartRepository, ProductPricingPort productPricingPort) {
        this.cartRepository = cartRepository;
        this.productPricingPort = productPricingPort;
    }

    @Override
    public Cart createCart() {
        Cart cart = new Cart(null);
        return cartRepository.save(cart);
    }

    @Override
    @Transactional(readOnly = true)
    public Cart getCart(CartId cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found: " + cartId.value()));
    }

    @Override
    public Cart addProductToCart(CartId cartId, ProductId productId, int quantity) {
        Cart cart = getCart(cartId);
        Money unitPrice = productPricingPort.getProductPrice(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId.value()));
        
        cart.addProduct(productId, quantity, unitPrice);
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeProductFromCart(CartId cartId, ProductId productId) {
        Cart cart = getCart(cartId);
        cart.removeProduct(productId);
        return cartRepository.save(cart);
    }
}
