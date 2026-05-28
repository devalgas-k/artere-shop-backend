package com.artere.shop.catalogue.domain.model;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record StockQuantity(int value) {
    public StockQuantity {
        if (value < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
    }

    public StockQuantity add(int amount) {
        return new StockQuantity(this.value + amount);
    }

    public StockQuantity subtract(int amount) {
        if (this.value - amount < 0) {
            throw new IllegalArgumentException("Insufficient stock");
        }
        return new StockQuantity(this.value - amount);
    }
}
