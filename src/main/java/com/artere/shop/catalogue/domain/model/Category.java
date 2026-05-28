package com.artere.shop.catalogue.domain.model;

import org.jmolecules.ddd.annotation.AggregateRoot;

import java.util.Objects;
import java.util.Optional;

@AggregateRoot
public class Category {
    private final CategoryId id;
    private String name;
    private String description;
    private CategoryId parentId;

    public Category(CategoryId id, String name, String description, CategoryId parentId) {
        Objects.requireNonNull(id, "Id cannot be null");
        this.id = id;
        setName(name);
        this.description = description;
        setParentId(parentId);
    }

    public CategoryId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Optional<CategoryId> getParentId() {
        return Optional.ofNullable(parentId);
    }

    public void setParentId(CategoryId parentId) {
        if (parentId != null && parentId.equals(this.id)) {
            throw new IllegalArgumentException("Category cannot be its own parent");
        }
        this.parentId = parentId;
    }
}
