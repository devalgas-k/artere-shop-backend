package com.artere.shop.cart.infrastructure.adapter.in.web;

import com.artere.shop.cart.domain.model.Cart;
import com.artere.shop.cart.domain.model.CartId;
import com.artere.shop.cart.domain.port.in.CartUseCase;
import com.artere.shop.cart.infrastructure.adapter.in.web.dto.AddCartItemRequest;
import com.artere.shop.cart.infrastructure.adapter.in.web.dto.CartItemResponse;
import com.artere.shop.cart.infrastructure.adapter.in.web.dto.CartResponse;
import com.artere.shop.catalogue.domain.model.ProductId;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    private final CartUseCase cartUseCase;

    public CartController(CartUseCase cartUseCase) {
        this.cartUseCase = cartUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartResponse createCart() {
        Cart cart = cartUseCase.createCart();
        return toResponse(cart);
    }

    @GetMapping("/{cartId}")
    public CartResponse getCart(@PathVariable Long cartId) {
        Cart cart = cartUseCase.getCart(new CartId(cartId));
        return toResponse(cart);
    }

    @PostMapping("/{cartId}/items")
    public CartResponse addProductToCart(
            @PathVariable Long cartId,
            @Valid @RequestBody AddCartItemRequest request) {
        Cart cart = cartUseCase.addProductToCart(
                new CartId(cartId),
                new ProductId(request.productId()),
                request.quantity()
        );
        return toResponse(cart);
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public CartResponse removeProductFromCart(
            @PathVariable Long cartId,
            @PathVariable Long productId) {
        Cart cart = cartUseCase.removeProductFromCart(
                new CartId(cartId),
                new ProductId(productId)
        );
        return toResponse(cart);
    }

    private CartResponse toResponse(Cart cart) {
        List<CartItemResponse> items = cart.getItems().stream()
                .map(item -> new CartItemResponse(
                        item.getProductId().value(),
                        item.getQuantity(),
                        item.getUnitPrice().amount(),
                        item.getTotalPrice().amount()
                )).toList();

        return new CartResponse(
                cart.getId() != null ? cart.getId().value() : null,
                cart.calculateTotal().amount(),
                items
        );
    }
}
