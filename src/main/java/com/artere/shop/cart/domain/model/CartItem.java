package com.artere.shop.cart.domain.model;

import com.artere.shop.catalogue.domain.model.ProductId;
import com.artere.shop.shared.domain.model.Money;
import org.jmolecules.ddd.annotation.Entity;

import java.util.Objects;

@Entity
public class CartItem {
    private final ProductId productId;
    private int quantity;
    private final Money unitPrice;

    public CartItem(ProductId productId, int quantity, Money unitPrice) {
        Objects.requireNonNull(productId, "ProductId cannot be null");
        Objects.requireNonNull(unitPrice, "UnitPrice cannot be null");
        this.productId = productId;
        this.unitPrice = unitPrice;
        setQuantity(quantity);
    }

    public ProductId getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
        this.quantity = quantity;
    }

    public Money getUnitPrice() {
        return unitPrice;
    }

    public Money getTotalPrice() {
        return unitPrice.multiply(quantity);
    }
}
