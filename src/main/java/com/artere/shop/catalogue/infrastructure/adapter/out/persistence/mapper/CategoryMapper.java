package com.artere.shop.catalogue.infrastructure.adapter.out.persistence.mapper;

import com.artere.shop.catalogue.domain.model.Category;
import com.artere.shop.catalogue.domain.model.CategoryId;
import com.artere.shop.catalogue.infrastructure.adapter.out.persistence.entity.CategoryJpaEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryMapper {

    public CategoryJpaEntity toEntity(Category category) {
        if (category == null) {
            return null;
        }
        CategoryJpaEntity entity = new CategoryJpaEntity();
        entity.setId(category.getId() != null ? category.getId().value() : null);
        entity.setName(category.getName());
        entity.setDescription(category.getDescription());
        entity.setParentId(category.getParentId().map(CategoryId::value).orElse(null));
        return entity;
    }

    public Category toDomain(CategoryJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Category(
            entity.getId() != null ? new CategoryId(entity.getId()) : null,
            entity.getName(),
            entity.getDescription(),
            entity.getParentId() != null ? new CategoryId(entity.getParentId()) : null
        );
    }
}