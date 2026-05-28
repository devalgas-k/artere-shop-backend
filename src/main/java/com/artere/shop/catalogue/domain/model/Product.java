package com.artere.shop.catalogue.domain.model;

import com.artere.shop.shared.domain.model.Money;
import org.jmolecules.ddd.annotation.AggregateRoot;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@AggregateRoot
public class Product {
    private final ProductId id;
    private String name;
    private Money price;
    private StockQuantity stockQuantity;
    private final Set<CategoryId> categoryIds;

    public Product(ProductId id, String name, Money price, StockQuantity stockQuantity) {
        Objects.requireNonNull(id, "Id cannot be null");
        this.id = id;
        setName(name);
        setPrice(price);
        setStockQuantity(stockQuantity);
        this.categoryIds = new HashSet<>();
    }

    public ProductId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        this.name = name;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        Objects.requireNonNull(price, "Price cannot be null");
        this.price = price;
    }

    public StockQuantity getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(StockQuantity stockQuantity) {
        Objects.requireNonNull(stockQuantity, "Stock quantity cannot be null");
        this.stockQuantity = stockQuantity;
    }

    public Set<CategoryId> getCategoryIds() {
        return Collections.unmodifiableSet(categoryIds);
    }

    public void assignToCategory(CategoryId categoryId) {
        Objects.requireNonNull(categoryId, "CategoryId cannot be null");
        this.categoryIds.add(categoryId);
    }

    public void removeFromCategory(CategoryId categoryId) {
        Objects.requireNonNull(categoryId, "CategoryId cannot be null");
        this.categoryIds.remove(categoryId);
    }
}
