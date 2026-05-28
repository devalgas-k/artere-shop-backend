package com.artere.shop.catalogue.domain.model;

import org.jmolecules.ddd.annotation.ValueObject;
import java.util.Objects;

@ValueObject
public record ProductId(Long value) {
    public ProductId {
        Objects.requireNonNull(value, "ProductId cannot be null");
    }
}
