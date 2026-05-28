package com.artere.shop.cart.domain.model;

import org.jmolecules.ddd.annotation.ValueObject;
import java.util.Objects;

@ValueObject
public record CartId(Long value) {
    public CartId {
        Objects.requireNonNull(value, "CartId cannot be null");
    }
}
