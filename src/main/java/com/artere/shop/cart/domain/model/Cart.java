package com.artere.shop.cart.domain.model;

import com.artere.shop.catalogue.domain.model.ProductId;
import com.artere.shop.shared.domain.model.Money;
import org.jmolecules.ddd.annotation.AggregateRoot;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AggregateRoot
public class Cart {
    private final CartId id;
    private final Instant createdAt;
    private final List<CartItem> items;

    public Cart(CartId id) {
        Objects.requireNonNull(id, "Id cannot be null");
        this.id = id;
        this.createdAt = Instant.now();
        this.items = new ArrayList<>();
    }
    
    public Cart(CartId id, Instant createdAt, List<CartItem> items) {
        Objects.requireNonNull(id, "Id cannot be null");
        Objects.requireNonNull(createdAt, "CreatedAt cannot be null");
        this.id = id;
        this.createdAt = createdAt;
        this.items = new ArrayList<>(items);
    }

    public CartId getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addProduct(ProductId productId, int quantity, Money unitPrice) {
        Objects.requireNonNull(productId, "ProductId cannot be null");
        Objects.requireNonNull(unitPrice, "UnitPrice cannot be null");
        
        Optional<CartItem> existingItem = items.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.orElseThrow();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            items.add(new CartItem(productId, quantity, unitPrice));
        }
    }

    public void removeProduct(ProductId productId) {
        Objects.requireNonNull(productId, "ProductId cannot be null");
        items.removeIf(item -> item.getProductId().equals(productId));
    }

    public Money calculateTotal() {
        return items.stream()
                .map(CartItem::getTotalPrice)
                .reduce(Money.zero(), Money::add);
    }
}
