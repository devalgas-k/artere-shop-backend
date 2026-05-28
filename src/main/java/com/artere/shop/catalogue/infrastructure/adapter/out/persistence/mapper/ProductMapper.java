package com.artere.shop.catalogue.infrastructure.adapter.out.persistence.mapper;

import com.artere.shop.catalogue.domain.model.CategoryId;
import com.artere.shop.catalogue.domain.model.Product;
import com.artere.shop.catalogue.domain.model.ProductId;
import com.artere.shop.catalogue.domain.model.StockQuantity;
import com.artere.shop.shared.domain.model.Money;
import com.artere.shop.catalogue.infrastructure.adapter.out.persistence.entity.ProductJpaEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.Set;

@Component
public class ProductMapper {

    public ProductJpaEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }
        ProductJpaEntity entity = new ProductJpaEntity();
        entity.setId(product.getId() != null ? product.getId().value() : null);
        entity.setName(product.getName());
        entity.setPrice(product.getPrice() != null ? product.getPrice().amount() : null);
        entity.setStockQuantity(product.getStockQuantity() != null ? product.getStockQuantity().value() : 0);
        
        if (product.getCategoryIds() != null) {
            Set<Long> categoryIds = product.getCategoryIds().stream()
                .map(CategoryId::value)
                .collect(Collectors.toSet());
            entity.setCategoryIds(categoryIds);
        }
        return entity;
    }

    public Product toDomain(ProductJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        Product product = new Product(
            entity.getId() != null ? new ProductId(entity.getId()) : null,
            entity.getName(),
            entity.getPrice() != null ? new Money(entity.getPrice()) : null,
            new StockQuantity(entity.getStockQuantity())
        );
        
        if (entity.getCategoryIds() != null) {
            for (Long catId : entity.getCategoryIds()) {
                product.assignToCategory(new CategoryId(catId));
            }
        }
        return product;
    }
}