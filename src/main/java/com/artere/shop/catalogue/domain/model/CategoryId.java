package com.artere.shop.catalogue.domain.model;

import org.jmolecules.ddd.annotation.ValueObject;
import java.util.Objects;

@ValueObject
public record CategoryId(Long value) {
    public CategoryId {
        Objects.requireNonNull(value, "CategoryId cannot be null");
    }
}
